package com.example.newskotlin.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newskotlin.R
import com.example.newskotlin.model.Category
import com.example.newskotlin.tools.CategoryClickListener

class CategoryAdapter(
    private val categoryList: ArrayList<Category>,
    private val listener: CategoryClickListener,
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val row =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_category, parent, false)
        return CategoryViewHolder(row)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        if (position == 0) {
            holder.viewColor.setBackgroundColor(Color.parseColor("#FFFFFF"))
            holder.tvName.text = "All news"
        } else {
            val category = categoryList[position - 1]
            holder.bindCategory(category)
        }
    }

    override fun getItemCount(): Int = categoryList.size + 1

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val viewColor: View = itemView.findViewById(R.id.viewColor)
        val tvName: TextView = itemView.findViewById(R.id.textViewName)

        fun bindCategory(category: Category) {
            tvName.text = category.title
            viewColor.setBackgroundColor(Color.parseColor(category.color))
        }

        init {
            itemView.setOnClickListener {

                if (adapterPosition == 0) {
                    listener.onCategoryClicked("all")
                } else {
                    listener.onCategoryClicked(categoryList[adapterPosition - 1].title)
                }
            }
        }
    }
}
