package com.example.newskotlin.ui.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newskotlin.R
import com.example.newskotlin.data.NewsContainer
import com.example.newskotlin.model.News
import com.example.newskotlin.tools.NewsClickListener
import com.example.newskotlin.ui.adapter.CategoryAdapter
import com.example.newskotlin.ui.adapter.NewsAdapter

class MainActivity : AppCompatActivity(), NewsClickListener {

    //ui

    private lateinit var recyclerViewNews: RecyclerView
    private lateinit var recyclerViewCategory: RecyclerView
    private lateinit var btSmallNews: LinearLayout
    private lateinit var btBigNews: LinearLayout
    private lateinit var svNews: SearchView
    private lateinit var tvNoResults: TextView

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
        setSearchView()

    }

    private fun setSearchView() {
        svNews.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                newsList = NewsContainer.filterListByTerm(p0.toString())
                newsAdapter.filterList(newsList)

                if (newsList.size == 0) {
                    tvNoResults.visibility = View.VISIBLE
                } else {
                    tvNoResults.visibility = View.GONE
                }

                return false
            }

        })
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
        svNews = findViewById(R.id.searchView)
        tvNoResults = findViewById(R.id.textViewNoResults)
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
                newsList = NewsContainer.filterListByCategory(it)
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