package com.yumelabs.cleanbox.filereader

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yumelabs.cleanbox.R

class ImageAdapter (private val context: Context, private val imageList: List<Image>):
    RecyclerView.Adapter<ImageAdapter.ContentViewHolder>() {
    private var onItemClickListener: OnItemClickListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.image, parent, false)
        return onItemClickListener?.let { ContentViewHolder(view, it) }!!
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
       val imgsrc = imageList[position].path
        Glide.with(context)
            .load(imgsrc)
            .placeholder(R.drawable.ic_collections)
            .error(R.drawable.ic_broken_image)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    inner class ContentViewHolder(itemView: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView)  {
        var view: View = itemView
        val imageView: ImageView = itemView.findViewById(R.id.imgThumbnail)

        init {
            itemView.setOnClickListener{ v: View ->
                val  position:Int = adapterPosition
                if (position!= RecyclerView.NO_POSITION){
                    listener.onItemClick(position)
                }
            }
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
}