package com.benyq.core.extentions

import android.app.Application
import android.content.res.Resources
import android.os.Build
import android.util.TypedValue
import androidx.annotation.ChecksSdkIntAtLeast


lateinit var appCtx: Application

fun fromTIRAMISU() = fromSpecificVersion(Build.VERSION_CODES.TIRAMISU)
fun beforeTIRAMISU() = beforeSpecificVersion(Build.VERSION_CODES.TIRAMISU)
fun fromSV2() = fromSpecificVersion(Build.VERSION_CODES.S_V2)
fun beforeSV2() = beforeSpecificVersion(Build.VERSION_CODES.S_V2)
fun fromS() = fromSpecificVersion(Build.VERSION_CODES.S)
fun beforeS() = beforeSpecificVersion(Build.VERSION_CODES.S)
fun fromR() = fromSpecificVersion(Build.VERSION_CODES.R)
fun beforeR() = beforeSpecificVersion(Build.VERSION_CODES.R)
fun fromQ() = fromSpecificVersion(Build.VERSION_CODES.Q)
fun beforeQ() = beforeSpecificVersion(Build.VERSION_CODES.Q)
fun fromM() = fromSpecificVersion(Build.VERSION_CODES.M)
fun beforeM() = beforeSpecificVersion(Build.VERSION_CODES.M)
fun fromN() = fromSpecificVersion(Build.VERSION_CODES.N)
fun beforeN() = beforeSpecificVersion(Build.VERSION_CODES.N)
fun fromO() = fromSpecificVersion(Build.VERSION_CODES.O)
fun beforeO() = beforeSpecificVersion(Build.VERSION_CODES.O)
fun fromP() = fromSpecificVersion(Build.VERSION_CODES.P)
fun beforeP() = beforeSpecificVersion(Build.VERSION_CODES.P)
@ChecksSdkIntAtLeast(parameter = 0)
fun fromSpecificVersion(version: Int): Boolean = Build.VERSION.SDK_INT >= version
fun beforeSpecificVersion(version: Int): Boolean = Build.VERSION.SDK_INT < version



/**
 * dp to px
 */
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Float.px: Float
    get() = this * Resources.getSystem().displayMetrics.density

val Float.sp: Float
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics)