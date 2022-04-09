package com.example.newskotlin.model

import java.io.Serializable

class News(
    var title: String = "",
    var description: String = "",
    var content: String = "",
    var imageUrl: String = "",
    var category: String = "",
    var portal: String = "",
    var url: String = "",
    var timePublished: String = "",
) : Serializable {
}