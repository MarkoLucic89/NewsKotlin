package com.example.newskotlin.ui.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newskotlin.R
import com.example.newskotlin.data.NewsContainer
import com.example.newskotlin.model.Category
import com.example.newskotlin.model.News
import com.example.newskotlin.tools.CategoryClickListener
import com.example.newskotlin.tools.NewsClickListener
import com.example.newskotlin.ui.adapter.CategoryAdapter
import com.example.newskotlin.ui.adapter.NewsAdapter

class MainActivity : AppCompatActivity(), NewsClickListener {

    //ui

    private lateinit var recyclerViewNews: RecyclerView
    private lateinit var recyclerViewCategory: RecyclerView
    private lateinit var btSmallNews: LinearLayout
    private lateinit var btBigNews: LinearLayout

    //vars

    private lateinit var newsAdapter: NewsAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var newsList: ArrayList<News>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNewsData()
        initUi()
        initNewsRecyclerView(R.layout.rv_item_small_news)
        initCategoryRecyclerView()
        setClickListeners()
    }

    private fun initNewsData() {
        NewsContainer.loadNews()
        NewsContainer.loadCategoryList()
        newsList = NewsContainer.newsList
    }

    private fun initUi() {
        recyclerViewNews = findViewById(R.id.recyclerViewNews)
        recyclerViewCategory = findViewById(R.id.recyclerViewCategory)
        btSmallNews = findViewById(R.id.linearLayoutSmallNews)
        btBigNews = findViewById(R.id.linearLayoutBigNews)
    }

    private fun initNewsRecyclerView(layout: Int) {
        recyclerViewNews.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            newsAdapter = NewsAdapter(newsList, this@MainActivity, layout)
            adapter = newsAdapter
        }
    }

    private fun initCategoryRecyclerView() {
        recyclerViewCategory.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)

            categoryAdapter = CategoryAdapter(NewsContainer.categoryList) {
                newsList = NewsContainer.filterList(it)
                newsAdapter.filterList(newsList)
            }

            adapter = categoryAdapter
        }
    }

    private fun setClickListeners() {
        btSmallNews.setOnClickListener { initNewsRecyclerView(R.layout.rv_item_small_news) }
        btBigNews.setOnClickListener { initNewsRecyclerView(R.layout.rv_item_big_news) }
    }

    override fun onNewsClicked(news: News) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(news.link)))
    }

//    override fun onCategoryClicked(category: Category) {
//        newsList = NewsContainer.filterList(category.name)
//        newsAdapter.filterList(newsList)
//    }
}