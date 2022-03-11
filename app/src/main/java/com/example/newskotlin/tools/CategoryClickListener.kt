package com.example.newskotlin.tools

import com.example.newskotlin.model.Category

interface CategoryClickListener {
    fun onCategoryClicked(category: Category)
}