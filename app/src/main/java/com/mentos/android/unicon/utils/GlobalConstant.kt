package com.mentos.android.unicon.utils

import android.animation.TimeInterpolator
import kotlin.math.sin

class GlobalConstant {
    companion object{
        //common
        const val FIREBASE_STORAGE_URL = "gs://example.com/"
        const val DEBUG_TAG = "DEBUG://"
        const val FLAG_NETWORK_ERROR = 404

        const val TAG = "MOBILE_TEMPLATE_APP"
        const val X_ACCESS_TOKEN = "X-ACCESS-TOKEN"
        const val NICKNAME = "NICKNAME"
        const val USER = "USER"

        //SharedPref Key
        const val FIRST_LAUNCH = "FIRST-LAUNCH"
        const val NO_MORE_TUTORIAL = "NO-MORE-TUTORIAL"

        //FirebaseStorage Folder
        const val EXAM_FOLDER = "test/exam"
        //const val EXAM_FOLDER = "prod/exam"

        //Animation
        const val FREQ = 1.5f
        const val DECAY = 1f
        val decayingSineWave = TimeInterpolator { input ->
            val raw = sin(FREQ * input * 2 * Math.PI)
            (raw * Math.exp((-input * DECAY).toDouble())).toFloat()
        }
    }

}