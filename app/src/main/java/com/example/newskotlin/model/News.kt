package com.example.newskotlin.model

import java.io.Serializable

class News(
    var title: String = "",
    var description: String = "",
    var content: String = "",
    var imageUrl: String = "",
    category: String,
    var portal: String = "",
    var link: String = "",
    var date: String = "",
) : Serializable {
    var category: Category = Category(category)
}