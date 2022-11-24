package com.example.assignment7.network.models


import com.google.gson.annotations.SerializedName

data class User(
    val avatar: String,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    val id: Long,
    @SerializedName("last_name")
    val lastName: String
)