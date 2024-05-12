package com.yumelabs.cleanbox

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log

fun openAccessibilitySettings(context: Context){
    val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
    context.startActivity(intent)
}

private const val TAG = "awearsense"

fun Any.logd(tag: String = TAG) {
    if (!BuildConfig.DEBUG) return
    if (this is String) {
        Log.d(tag, this)
    } else {
        Log.d(tag, this.toString())
    }
}



fun millisecondsToTime(milliseconds: Long): String {
    val minutes = milliseconds / 1000 / 60
    val seconds = milliseconds / 1000 % 60
    val secondsStr = seconds.toString()
    val secs: String = if (secondsStr.length >= 2) {
        secondsStr.substring(0, 2)
    } else {
        "0$secondsStr"
    }
    return "$minutes:$secs"
}
