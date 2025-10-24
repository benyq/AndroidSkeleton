package com.benyq.android.skeleton

import retrofit2.http.GET

interface WanAndroidService {

    @GET("banner/json")
    suspend fun banner(): DataResponse<List<Banner>>
}