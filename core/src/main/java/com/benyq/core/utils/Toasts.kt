package com.benyq.core.utils

import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.StringRes
import com.benyq.core.extentions.appCtx

object Toasts {

    private var toast: Toast? = null
    private val mainHandler = Handler(Looper.getMainLooper())

    fun show(msg: String) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            realShow(msg)
        }else {
            mainHandler.post {
                realShow(msg)
            }
        }
    }

    fun show(@StringRes resId: Int) {
        show(appCtx.getString(resId))
    }

    private fun realShow(msg: String) {
        if (toast == null) {
            toast = Toast.makeText(appCtx, msg, Toast.LENGTH_SHORT)
            toast?.setGravity(Gravity.CENTER, 0, 0)
        }else {
            toast?.cancel()
        }
        toast?.apply {
            setText(msg)
            show()
        }
    }

}