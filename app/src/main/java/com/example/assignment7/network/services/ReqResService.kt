package com.example.assignment7.network.services

import com.example.assignment7.network.models.BaseReqRes
import com.example.assignment7.network.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqResService {

    @GET("users")
    suspend fun getUsers(@Query("page") page: Int): Response<BaseReqRes<List<User>>>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Long): Response<BaseReqRes<User>>

}