package com.yumelabs.cleanbox

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.permissionx.guolindev.PermissionX
import com.yumelabs.cleanbox.databinding.ActivityMainBinding
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dataViewModel: DataViewModel by viewModels()
    private val asyncTask: AsyncTask by lazy { ViewModelProvider(this)[AsyncTask::class.java] }

    private val imageList:ArrayList<Image> = arrayListOf()
    private var imageAdapter: ImageAdapter? = null
    private var linearLayoutManager: LinearLayoutManager?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            listOf(
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
            )
        } else {
            listOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
            )
        }
        checkPermissions(permissions)
    }

    override fun onResume() {
        super.onResume()
        loadImages()
        imageInit()
    }

    private fun imageInit(){
        /*-----Music player code----*/
        linearLayoutManager = GridLayoutManager(this, 2)
        imageAdapter = ImageAdapter(this, imageList)

        binding.musicListView.apply{
            adapter = imageAdapter
            layoutManager = linearLayoutManager
        }

        imageAdapter?.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(pos: Int) {
                "$pos".logd()
            }
        })
    }

    /*---------------------------*/
    private fun loadImages(){
        asyncTask.execute(onPreExecute = {
            binding.fileReading.visibility = View.VISIBLE
        }, doInBackground = {
            fileReader()
        }, onPostExecute = {
            binding.fileReading.visibility = View.GONE
            it.apply {
                imageList.clear()
                it.forEachIndexed { index, song ->
                    imageList.add(song)
                    imageAdapter?.notifyItemChanged(index)
                }
            }
        })
    }

    private fun fileReader():ArrayList<Image>{
     val tempList:ArrayList<Image> = arrayListOf()
     val imageURIs = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.ImageColumns.DATA,MediaStore.Images.Media.DISPLAY_NAME)
        var cursor = contentResolver.query(imageURIs,projection,null,null,null)
        cursor?.apply {
            try{
                moveToFirst()
                do {
                    val path = getString(getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                    val fileName = getString(getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                    tempList.add(Image(fileName,path,0))
                }while (moveToNext())
                close()
            }catch (_:IOException){
                "_".logd("error")
            }
        }
        return  tempList
    }

    private fun checkPermissions(permissions: List<String>){
        PermissionX.init(this)
            .permissions(permissions)
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    "SuccessFull".logd("Permission")
                } else {
                    "Failed $deniedList".logd("Permission")
                }
            }
    }
}