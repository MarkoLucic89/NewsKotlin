package com.example.newskotlin.networking

import com.example.newskotlin.model.News
import com.example.newskotlin.model.ResponseNews
import com.example.newskotlin.model.ResponseSettings
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsService {

    @GET("api/news")
    fun getAllNews(
        @Query("rows") rows: Int,
        @Query("page") page: Int,
    ): Call<ResponseNews>

    @GET("api/properties")
    fun getSettings(): Call<ResponseSettings>

    @GET("api/news")
    fun getNewsFromCategory(
        @Query("categories[]") category: String,
        @Query("rows") rows: Int,
        @Query("page") page: Int,
    ): Call<ResponseNews>

}