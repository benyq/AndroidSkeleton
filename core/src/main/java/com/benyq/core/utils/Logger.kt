package com.benyq.core.utils

import android.util.Log
import com.benyq.core.BuildConfig

/**
 *
 * @author benyq
 * @date 4/11/2024
 *
 */
object Logger {
    private var tag = "Core_Kit"
    private var enableLog = BuildConfig.DEBUG

    @JvmStatic
    fun setup(enable: Boolean, tag: String) {
        this.tag = tag
        this.enableLog = enable
    }

    private fun obj2String(o: Any?): String? {
        return when {
            o == null -> null
            o is String -> o
            o is Number -> o.toString()
            o is Boolean -> o.toString()
            o.javaClass.isAnonymousClass -> {
                val s = o.toString()
                s.substring(s.lastIndexOf('.'))
            }
            o is Class<*> -> o.simpleName
            else -> o.toString()
        }
    }

    @JvmStatic
    fun v(o: Any, method: String, vararg args: Any) {
        if (enableLog) {
            Log.v(tag, createLog(o, method, *args))
        }
    }

    @JvmStatic
    fun d(o: Any, method: String, vararg args: Any?) {
        if (enableLog) {
            Log.d(tag, createLog(o, method, *args))
        }
    }

    @JvmStatic
    fun i(o: Any, method: String, vararg args: Any?) {
        if (enableLog) {
            Log.i(tag, createLog(o, method, *args))
        }
    }

    @JvmStatic
    fun w(o: Any, method: String, vararg args: Any?) {
        if (enableLog) {
            Log.w(tag, createLog(o, method, *args))
        }
    }

    @JvmStatic
    fun e(o: Any, method: String, vararg args: Any?) {
        if (enableLog) {
            Log.e(tag, createLog(o, method, *args))
        }
    }

    private fun createLog(o: Any, method: String, vararg args: Any?): String {
        val msg = StringBuilder("[" + obj2String(o) + "]").append(" -> ").append(method)
        for (arg in args) {
            msg.append(" -> ").append(obj2String(arg))
        }
        return msg.toString()
    }
}