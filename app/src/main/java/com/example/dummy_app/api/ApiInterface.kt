package com.example.dummy_app.api

import com.example.dummy_app.model.User
import com.example.dummy_app.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("users")
    suspend fun getUsers(): Response<UserResponse>
}