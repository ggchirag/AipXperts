package com.example.aipxperts

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("/comments")
    fun GetResponse(): Call<List<response_model>>
}