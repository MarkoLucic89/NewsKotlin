package com.example.newskotlin.data

import com.example.newskotlin.model.Category
import com.example.newskotlin.model.News
import com.example.newskotlin.model.Portal
import java.util.*
import kotlin.collections.ArrayList

class DataContainer {

    companion object {


        var newsList: ArrayList<News> = ArrayList()
        var categoryList: ArrayList<Category> = ArrayList()
        var portalList:ArrayList<Portal> = ArrayList()


        fun filterListByCategory(category: Category?): ArrayList<News> {
            var list: ArrayList<News> = ArrayList()

            if (category == null) {
                return newsList
            }

            for (news in newsList) {
                if (news.category == category.title) {
                    list.add(news)
                }
            }

            return list
        }

        fun filterListByTerm(term: String?): ArrayList<News> {
            var list: ArrayList<News> = ArrayList()

            for (news in newsList) {
                if (news.title.lowercase(Locale.getDefault()).contains(term!!.lowercase(Locale.getDefault()))) {
                    list.add(news)
                }
            }

            return list
        }


    }
}