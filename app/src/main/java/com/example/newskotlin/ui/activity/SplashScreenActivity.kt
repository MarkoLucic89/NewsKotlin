package com.example.newskotlin.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.newskotlin.R
import com.example.newskotlin.data.DataContainer
import com.example.newskotlin.model.ResponseNews
import com.example.newskotlin.model.ResponseSettings
import com.example.newskotlin.networking.NewsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class SplashScreenActivity : AppCompatActivity() {

    private val TAG: String = "SplashScreenActivity"

    private lateinit var service: NewsService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        initNewsData()
    }

    private fun initNewsData() {

        initNewsRetrofitService()

        service.getSettings().enqueue(object : Callback<ResponseSettings> {
            override fun onResponse(
                call: Call<ResponseSettings>,
                response: Response<ResponseSettings>,
            ) {
                DataContainer.categoryList = response.body()!!.categories
                DataContainer.portalList = response.body()!!.portals


                service.getAllNews(5, 0).enqueue(object : Callback<ResponseNews> {
                    override fun onResponse(
                        call: Call<ResponseNews>,
                        response: Response<ResponseNews>,
                    ) {
                        DataContainer.newsList = response.body()!!.news

                        startActivity(Intent(applicationContext, MainActivity::class.java))
                        this@SplashScreenActivity.finish()
                    }

                    override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                        Log.d(TAG, "onFailure: ${t.message}")
                    }

                })
            }

            override fun onFailure(call: Call<ResponseSettings>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }


        })
    }

    private fun initNewsRetrofitService() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.brzevesti.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

         service = retrofit.create(NewsService::class.java)
    }
}