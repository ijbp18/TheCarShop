package com.home.thecarshop.presentation.addnewcar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.home.thecarshop.R
import com.home.thecarshop.model.Category
import com.home.thecarshop.presentation.addnewcar.holders.CategoryViewHolder
import com.home.thecarshop.utilities.OnItemSelected

class CategoryListAdapter(private val onItemSelectedListener: OnItemSelected<Category>) : RecyclerView.Adapter<CategoryViewHolder>(){

    private var categoryItems: List<Category> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_container_category,
                parent,
                false
            ), onItemSelectedListener
        )


    override fun getItemCount(): Int = categoryItems.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
        holder.bind(categoryItems[position])

    fun setData(items: List<Category>) {
        this.categoryItems = items
        notifyDataSetChanged()
    }

    fun unSelectAllDistinctTo(item: Category) {
        categoryItems
            .forEach {
                if (!(item === it)) {
                    it.isSelected = false
                }
            }
    }

}