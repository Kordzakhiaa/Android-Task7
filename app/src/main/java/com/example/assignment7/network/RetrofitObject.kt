package com.example.assignment7.network

import com.example.assignment7.network.services.ReqResService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {

    private lateinit var retrofit: Retrofit
    private lateinit var client: OkHttpClient
    val reqResService: ReqResService get() =  getService(ReqResService::class.java)

    fun init() {
        client = OkHttpClient.Builder().build()
        retrofit = Retrofit.Builder().baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()
    }

    private fun <T> getService(service: Class<T>): T {
        return retrofit.create(service)
    }

}