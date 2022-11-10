package com.example.postdatausingretrofit.data.remote

import com.example.postdatausingretrofit.data.model.Users
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {
    @POST("users")
    fun createUsers(@Body users: Users): Call<Users>
}