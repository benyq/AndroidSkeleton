package com.benyq.core

import android.app.Application
import com.benyq.core.extentions.appCtx
import com.tencent.mmkv.MMKV

/**
 * 初始化
 */
object Core {

    @JvmStatic
    fun setup(app: Application) {
        appCtx = app
        MMKV.initialize(app)
    }

}