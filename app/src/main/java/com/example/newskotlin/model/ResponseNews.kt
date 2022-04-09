package com.example.newskotlin.model

class ResponseNews(
    var totalNews:Int,
    var page:Int,
    var raws:Int,
    var orderBy:String,
    var news:ArrayList<News>
)