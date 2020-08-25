package com.home.thecarshop.presentation.addnewcar.holders

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.home.thecarshop.R
import com.home.thecarshop.model.Condition
import com.home.thecarshop.utilities.OnItemSelected

class ConditionViewHolder(
    private val view: View,
    private val onItemSelectedListener: OnItemSelected<Condition>
): RecyclerView.ViewHolder(view) {

    fun bind(item: Condition) {
        view.setOnClickListener {
            onItemSelectedListener.onItemSelected(item)
        }

        if (item.isSelected) {
            view.findViewById<CardView>(R.id.conditionRowContainer)
                .setCardBackgroundColor(ContextCompat.getColor(view.context, R.color.colorAccent))
        }
        else {
            view.findViewById<CardView>(R.id.conditionRowContainer)
                .setCardBackgroundColor(ContextCompat.getColor(view.context, R.color.translucent_white))
        }

        val title = view.findViewById<TextView>(R.id.txt_place_name)
        title.text = item.description


    }
}