package com.example.newskotlin.tools

import com.example.newskotlin.model.Category

interface NextPageListener {
    fun loadNewsFromNextPage(page:Int)
}