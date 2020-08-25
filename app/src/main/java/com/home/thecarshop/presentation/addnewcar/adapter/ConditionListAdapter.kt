package com.home.thecarshop.presentation.addnewcar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.home.thecarshop.R
import com.home.thecarshop.model.Condition
import com.home.thecarshop.presentation.addnewcar.holders.ConditionViewHolder
import com.home.thecarshop.utilities.OnItemSelected

class ConditionListAdapter(private val onItemSelectedListener: OnItemSelected<Condition>) : RecyclerView.Adapter<ConditionViewHolder>(){

    private var conditionItems: List<Condition> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConditionViewHolder =
        ConditionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_condition,
                parent,
                false
            ), onItemSelectedListener
        )


    override fun getItemCount(): Int = conditionItems.size

    override fun onBindViewHolder(holder: ConditionViewHolder, position: Int) =
        holder.bind(conditionItems[position])

    fun setData(items: List<Condition>) {
        this.conditionItems = items
        notifyDataSetChanged()
    }

    fun unSelectAllDistinctTo(item: Condition) {
        conditionItems
            .forEach {
                if (!(item === it)) {
                    it.isSelected = false
                }
            }
    }

}