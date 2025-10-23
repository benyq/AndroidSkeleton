package com.benyq.core

import android.app.Application
import com.benyq.core.extentions.appCtx

/**
 * 初始化
 */
object Core {

    fun setup(app: Application) {
        appCtx = app
    }

}