package com.benyq.core.extentions

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Build
import android.view.Gravity
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.annotation.ColorInt
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.ColorUtils
import androidx.core.view.WindowCompat
import androidx.core.view.WindowCompat.setDecorFitsSystemWindows
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume


suspend fun Context.alert(title: String, message: String): Boolean =
    suspendCancellableCoroutine { continuation ->
        AlertDialog.Builder(this)
            .setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
                continuation.resume(false)
            }.setPositiveButton("Yes") { dialog, which ->
                dialog.dismiss()
                continuation.resume(true)
            }.setTitle(title)
            .setMessage(message)
            .setOnCancelListener {
                continuation.resume(false)
            }.create()
            .also { dialog ->
                continuation.invokeOnCancellation {
                    dialog.dismiss()
                }
            }.show()
    }



fun Activity.showLoading() {
    contentView()?.apply {
        val pb = ProgressBar(context)
        pb.tag = "pb"
        addView(pb, FrameLayout.LayoutParams(150, 150, Gravity.CENTER))
    }
}

fun Activity.hideLoading() {
    val pb = contentView()?.findViewWithTag<ProgressBar>("pb")
    pb?.let { contentView()?.removeView(it) }
}

fun Activity.contentView(): FrameLayout? {
    return takeIf { !isFinishing && !isDestroyed }?.window?.decorView?.findViewById(android.R.id.content)
}

fun Activity.systemBarColor(@ColorInt color: Int) {
    val insetsController = WindowCompat.getInsetsController(window, window.decorView)
    window.statusBarColor = color
    window.navigationBarColor = color
    val luminance = ColorUtils.calculateLuminance(color)
    insetsController.isAppearanceLightStatusBars = luminance > 0.5
}

fun Activity.isAppearanceLightStatusBars(isLight: Boolean) {
    val insetsController = WindowCompat.getInsetsController(window, window.decorView)
    insetsController.isAppearanceLightStatusBars = isLight
}

fun Fragment.isAppearanceLightStatusBars(isLight: Boolean) {
    requireActivity().isAppearanceLightStatusBars(isLight)
}

fun Activity.fullScreen(fullScreen: Boolean = true) {
    val insetsController = WindowCompat.getInsetsController(window, window.decorView)
    if (fullScreen) {
        setDecorFitsSystemWindows(window, false)
        insetsController.hide(WindowInsetsCompat.Type.systemBars())
        insetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        //允许window 的内容可以上移到刘海屏状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val lp = window.attributes
            lp.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            window.attributes = lp
        }
    }else {
        setDecorFitsSystemWindows(window, true)
        insetsController.show(WindowInsetsCompat.Type.systemBars())
        insetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
    }

}