package com.yumelabs.cleanbox

import android.net.Uri

data class VideoContent(
    val uri: Uri,
    val name: String,
    val duration: Int,
    val size: Int
)
