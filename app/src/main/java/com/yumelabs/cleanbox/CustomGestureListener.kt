package com.yumelabs.cleanbox

import android.view.ScaleGestureDetector
import android.view.View
import com.yumelabs.cleanbox.common.logd

class CustomGestureListener(var view:View) : ScaleGestureDetector.SimpleOnScaleGestureListener() {

    private var factor = 1.0f

    override fun onScale(detector: ScaleGestureDetector): Boolean {
        val scaleFactor = detector.scaleFactor -1
        factor+= scaleFactor
        "factor $factor".logd("Gesture")
        view.scaleX = factor
        view.scaleY = factor
        return super.onScale(detector)
    }

    override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
        "factor Started Scaling".logd("Gesture")
        return true
    }
    
    override fun onScaleEnd(detector: ScaleGestureDetector) {
        "factor End Scaling".logd("Gesture")

        super.onScaleEnd(detector)
    }

}