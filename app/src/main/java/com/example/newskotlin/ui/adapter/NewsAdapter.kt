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
import com.example.newskotlin.tools.NextPageListener
import com.squareup.picasso.Picasso
import java.util.ArrayList

class NewsAdapter(
    private var newsList: List<News>,
    private val newsClickListener: NewsClickListener,
    private val nextPageListener: NextPageListener,
    private var layout: Int,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            val row =
                LayoutInflater.from(parent.context).inflate(layout, parent, false)
            NewsViewHolder(row)
        } else {
            val row =
                LayoutInflater.from(parent.context).inflate(R.layout.rv_item_loading, parent, false)
            LoadingViewHolder(row)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (position < newsList.size) {
            val news = newsList[position]
            val viewHolder: NewsViewHolder = holder as NewsViewHolder
            viewHolder.bindNews(news)
        } else if (position == newsList.size && position >= 5) {
            val viewHolder: LoadingViewHolder = holder as LoadingViewHolder
            viewHolder.loadNextPageNews()
        }
    }

    override fun getItemCount(): Int = newsList.size + 1

    fun filterList(filterList: ArrayList<News>) {
        this.newsList = filterList
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (newsList.size >= 5 && position == newsList.size) {
            return 1
        }
        return 0
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivPicture: ImageView = itemView.findViewById(R.id.imageViewPicture)
        private val tvCategory: TextView = itemView.findViewById(R.id.textViewCategory)
        private val tvTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        private val tvDescription: TextView? = itemView.findViewById(R.id.textViewDescription)
        private val tvDate: TextView = itemView.findViewById(R.id.textViewDate)

        init {
            itemView.setOnClickListener {
                newsClickListener.onNewsClicked(newsList[adapterPosition])
            }
        }

        fun bindNews(news: News) {
            Picasso.get().load(news.imageUrl).into(ivPicture)
            tvCategory.text = news.category
            tvTitle.text = news.title
            tvDescription?.text = news.description
            tvDate.text = news.timePublished
        }
    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun loadNextPageNews() {
            val page: Int = calculatePage(adapterPosition)
            nextPageListener.loadNewsFromNextPage(page)
        }

        private fun calculatePage(position: Int): Int = position / 5 + 1
    }
}
