package com.example.newskotlin.tools

import com.example.newskotlin.model.News

interface NewsClickListener {
    fun onNewsClicked(news: News)
}