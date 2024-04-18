package com.valentinerutto.roomdbtutorial.data.remote.network

import com.valentinerutto.roomdbtutorial.data.remote.LineResponse
import com.valentinerutto.roomdbtutorial.data.remote.RandomLineResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("list")
    suspend fun getPickupLines(
        @Query("page") page: Int = 1, @Query("perPage") perPage: Int = 100
    ): Response<LineResponse>

    @GET("random")
    suspend fun getRandomPickupLine(
    ): Response<RandomLineResponse>

}