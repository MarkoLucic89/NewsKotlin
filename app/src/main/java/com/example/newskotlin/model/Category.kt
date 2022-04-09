package com.example.newskotlin.model

import java.io.Serializable

class Category(
    var title: String = "",
    var color: String = "#FF3700B3",
    var subcategories: ArrayList<Category> = ArrayList()
) : Serializable {
}