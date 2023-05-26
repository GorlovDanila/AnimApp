package com.example.animapp.data.anim.datasource.remote

import com.example.animapp.data.anim.datasource.remote.response.AnimListResponse
import com.example.animapp.data.anim.datasource.remote.response.AnimResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val COUNT_ANIM = 10

interface AnimApi {

    @GET("top/anime")
    suspend fun getAnimList(
        @Query("limit") count: Int = COUNT_ANIM,
    ): AnimListResponse

    @GET("anime/{id}")
    suspend fun getAnimById(
        @Path("id") id: Int,
    ): AnimResponse
}
