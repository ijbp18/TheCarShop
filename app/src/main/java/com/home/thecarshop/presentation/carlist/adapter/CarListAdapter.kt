package com.home.thecarshop.presentation.carlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.home.thecarshop.R
import com.home.thecarshop.model.Car
import com.home.thecarshop.presentation.carlist.holders.CarViewHolder

class CarListAdapter : RecyclerView.Adapter<CarViewHolder>() {

    private var carItems: List<Car> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder =
        CarViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_container_car,
                parent,
                false
            )
        )


    override fun getItemCount(): Int = carItems.size

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) =
        holder.bind(carItems[position])

    fun setData(placeItems: List<Car>) {
        this.carItems = placeItems
        notifyDataSetChanged()
    }

}