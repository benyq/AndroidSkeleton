package com.benyq.core.http

interface SodaResponse<T> {

    fun isSuccess(): Boolean

    fun getMessage(): String

    fun getErrorCode(): Int

    fun getRealData(): T?

}