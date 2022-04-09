package com.example.newskotlin.ui.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newskotlin.R
import com.example.newskotlin.data.DataContainer
import com.example.newskotlin.model.Category
import com.example.newskotlin.model.News
import com.example.newskotlin.model.ResponseNews
import com.example.newskotlin.networking.NewsService
import com.example.newskotlin.tools.CategoryClickListener
import com.example.newskotlin.tools.NewsClickListener
import com.example.newskotlin.tools.NextPageListener
import com.example.newskotlin.ui.adapter.CategoryAdapter
import com.example.newskotlin.ui.adapter.NewsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), NewsClickListener, CategoryClickListener,
    NextPageListener {

    //ui

    private lateinit var recyclerViewNews: RecyclerView
    private lateinit var recyclerViewCategory: RecyclerView
    private lateinit var btSmallNews: LinearLayout
    private lateinit var btBigNews: LinearLayout
    private lateinit var svNews: SearchView
    private lateinit var tvNoResults: TextView

    //vars

    private val TAG: String = "MainActivity"
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var newsList: ArrayList<News>
    private lateinit var service: NewsService

    //temporary variables
    private var tempCategory = "all"
    private var isLoading = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNewsRetrofitService()
        initUi()
        initNewsRecyclerView(R.layout.rv_item_small_news)
        initCategoryRecyclerView()
        setClickListeners()
        setSearchView()

    }

    private fun initNewsRetrofitService() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.brzevesti.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(NewsService::class.java)
    }

    private fun setSearchView() {
        svNews.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                newsList = DataContainer.filterListByTerm(p0.toString())
                newsAdapter.filterList(newsList)

                if (newsList.size == 0) {
                    tvNoResults.visibility = View.VISIBLE
                    recyclerViewNews.visibility = View.INVISIBLE
                } else {
                    tvNoResults.visibility = View.GONE
                    recyclerViewNews.visibility = View.VISIBLE
                }

                return false
            }
        })
    }

    private fun initUi() {
        recyclerViewNews = findViewById(R.id.recyclerViewNews)
        recyclerViewCategory = findViewById(R.id.recyclerViewCategory)
        btSmallNews = findViewById(R.id.linearLayoutSmallNews)
        btBigNews = findViewById(R.id.linearLayoutBigNews)
        svNews = findViewById(R.id.searchView)
        tvNoResults = findViewById(R.id.textViewNoResults)
    }

    private fun initNewsRecyclerView(layout: Int) {
        recyclerViewNews.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            newsAdapter = NewsAdapter(DataContainer.newsList, this@MainActivity,this@MainActivity, layout)
            adapter = newsAdapter
        }
    }

    private fun initCategoryRecyclerView() {
        recyclerViewCategory.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            categoryAdapter = CategoryAdapter(DataContainer.categoryList, this@MainActivity)
            adapter = categoryAdapter
        }
    }

    private fun setClickListeners() {
        btSmallNews.setOnClickListener { initNewsRecyclerView(R.layout.rv_item_small_news) }
        btBigNews.setOnClickListener { initNewsRecyclerView(R.layout.rv_item_big_news) }
    }

    override fun onNewsClicked(news: News) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(news.url)))
    }

    override fun onCategoryClicked(category: String) {

        tempCategory = category

        service.getNewsFromCategory(category, 5, 1).enqueue(object : Callback<ResponseNews> {
            override fun onResponse(call: Call<ResponseNews>, response: Response<ResponseNews>) {
                DataContainer.newsList = response.body()!!.news
                newsAdapter.filterList(DataContainer.newsList)
            }

            override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })

    }

    override fun loadNewsFromNextPage(page: Int) {

        Log.d(TAG, "page: $page")

        if (isLoading) {
            return
        } else {
            isLoading = true
        }

        if (tempCategory == "all") {
            service.getAllNews(5, page).enqueue(object : Callback<ResponseNews> {
                override fun onResponse(
                    call: Call<ResponseNews>,
                    response: Response<ResponseNews>,
                ) {
                    DataContainer.newsList.addAll(response.body()!!.news)
                    newsAdapter.notifyDataSetChanged()
                    isLoading = false

                }

                override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                    isLoading = false
                }

            })
        } else {

            service.getNewsFromCategory(tempCategory, 5, page).enqueue(object : Callback<ResponseNews> {
                override fun onResponse(call: Call<ResponseNews>, response: Response<ResponseNews>) {
                    DataContainer.newsList.addAll(response.body()!!.news)
                    newsAdapter.notifyDataSetChanged()
                    isLoading = false
                }

                override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                    isLoading = false
                }

            })
        }


    }

}