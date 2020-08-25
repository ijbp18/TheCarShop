package com.home.thecarshop.presentation.addnewcar.holders

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.home.thecarshop.R
import com.home.thecarshop.model.Category
import com.home.thecarshop.utilities.OnItemSelected

class CategoryViewHolder(
    private val view: View,
    private val onItemSelectedListener: OnItemSelected<Category>
): RecyclerView.ViewHolder(view) {

    fun bind(item: Category) {

        if (item.isSelected) {
            view.findViewById<CardView>(R.id.categoryRowContainer)
                .setCardBackgroundColor(ContextCompat.getColor(view.context, R.color.colorAccent))
        }
        else {
            view.findViewById<CardView>(R.id.categoryRowContainer)
                .setCardBackgroundColor(ContextCompat.getColor(view.context, R.color.translucent_white))
        }

        view.setOnClickListener {
            onItemSelectedListener.onItemSelected(item)
        }

        val title = view.findViewById<TextView>(R.id.txtName)
        title.text = item.name


    }
}