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
    private val listener: (String) -> Unit,
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val row =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_category, parent, false)
        return CategoryViewHolder(row)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.bindCategory(category)
    }

    override fun getItemCount(): Int = categoryList.size

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val viewColor: View = itemView.findViewById(R.id.viewColor)
        private val tvName: TextView = itemView.findViewById(R.id.textViewName)

        fun bindCategory(category: Category) {
            tvName.text = category.name
            viewColor.setBackgroundColor(Color.parseColor(category.color))
        }

        init {
            itemView.setOnClickListener {
                listener(categoryList[adapterPosition].name)
            }
        }
    }
}
