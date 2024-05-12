package com.yumelabs.cleanbox

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataViewModel : ViewModel() {

    var imagelist: MutableLiveData<Image> = MutableLiveData()
 
}