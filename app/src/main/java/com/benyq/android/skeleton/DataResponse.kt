package com.benyq.android.skeleton

import com.benyq.core.http.SodaResponse

data class DataResponse<T>(
    private val errorCode: Int,
    private val errorMsg: String,
    private val data: T?,
) : SodaResponse<T> {
    override fun isSuccess(): Boolean {
        return errorCode == 0
    }

    override fun getMessage() = errorMsg

    override fun getErrorCode() = errorCode

    override fun getRealData(): T = data!!

    companion object {
        fun <T> success(data: T): DataResponse<T> {
            return DataResponse(0, "", data)
        }

        fun error(code: Int, message: String): DataResponse<*> {
            return DataResponse(code, message, null)
        }
    }

}
