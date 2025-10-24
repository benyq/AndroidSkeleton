package com.benyq.core.http


object ErrorHandler {
    lateinit var errorHandler: (Exception) -> SodaResponse<*>

    fun <T> handleError(e: Exception): SodaResponse<T> {
        @Suppress("UNCHECKED_CAST")
        return errorHandler.invoke(e) as SodaResponse<T>
    }
}

suspend fun<T> request(block: suspend () -> SodaResponse<T>): SodaResponse<T> {
    return try {
        block.invoke()
    } catch (e: Exception) {
        ErrorHandler.handleError(e)
    }
}