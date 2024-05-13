package com.yumelabs.cleanbox

import android.view.ScaleGestureDetector
import android.view.View
import com.yumelabs.cleanbox.common.logd

class CustomGestureListener() : ScaleGestureDetector.SimpleOnScaleGestureListener() {

    override fun onScale(detector: ScaleGestureDetector): Boolean {
        if (detector.getCurrentSpan() - detector.getPreviousSpan() < -1) {
            "Detected".logd("Gesture")
            return true
        }
//            if (detector.getCurrentSpan() - detector.getPreviousSpan() < -1) {
//                if (mCurrentLayoutManager == mGridLayoutManager1) {
//                    mCurrentLayoutManager = mGridLayoutManager2;
//                    mRvPhotos.setLayoutManager(mGridLayoutManager2);
//                    return true;
//                } else if (mCurrentLayoutManager == mGridLayoutManager2) {
//                    mCurrentLayoutManager = mGridLayoutManager3;
//                    mRvPhotos.setLayoutManager(mGridLayoutManager3);
//                    return true;
//                }
//                return true
//            } else if(detector.getCurrentSpan() - detector.getPreviousSpan() > 1) {
//                if (mCurrentLayoutManager == mGridLayoutManager3) {
//                    mCurrentLayoutManager = mGridLayoutManager2;
//                    mRvPhotos.setLayoutManager(mGridLayoutManager2);
//                    return true;
//                } else if (mCurrentLayoutManager == mGridLayoutManager2) {
//                    mCurrentLayoutManager = mGridLayoutManager1;
//                    mRvPhotos.setLayoutManager(mGridLayoutManager1);
//                    return true;
//                }
//            }
//        }
        return false;
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