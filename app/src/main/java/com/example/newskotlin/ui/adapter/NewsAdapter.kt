package com.example.newskotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newskotlin.R
import com.example.newskotlin.model.News
import com.example.newskotlin.tools.NewsClickListener
import com.squareup.picasso.Picasso
import java.util.ArrayList

class NewsAdapter(
    private var newsList: List<News>,
    private val listener: NewsClickListener,
    private var layout: Int,
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val row =
            LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return NewsViewHolder(row)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.bindNews(news)
    }

    override fun getItemCount(): Int = newsList.size

    fun filterList(filterList: ArrayList<News>) {
        this.newsList = filterList
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivPicture: ImageView = itemView.findViewById(R.id.imageViewPicture)
        private val tvCategory: TextView = itemView.findViewById(R.id.textViewCategory)
        private val tvTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        private val tvDescription: TextView? = itemView.findViewById(R.id.textViewDescription)
        private val tvDate: TextView = itemView.findViewById(R.id.textViewDate)

        init {
            itemView.setOnClickListener {
                listener.onNewsClicked(newsList[adapterPosition])
            }
        }

        fun bindNews(news: News) {
            Picasso.get().load(news.imageUrl).into(ivPicture)
            tvCategory.text = news.category.name
            tvTitle.text = news.title
            tvDescription?.text = news.description
            tvDate.text = news.date
        }
    }
}
