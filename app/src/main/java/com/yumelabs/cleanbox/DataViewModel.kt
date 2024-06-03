package com.yumelabs.cleanbox

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yumelabs.cleanbox.filereader.Image

class DataViewModel : ViewModel() {

    var imagelist: MutableLiveData<Image> = MutableLiveData()
 
}