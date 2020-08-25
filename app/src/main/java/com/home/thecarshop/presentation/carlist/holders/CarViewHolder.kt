package com.home.thecarshop.presentation.carlist.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.home.thecarshop.R
import com.home.thecarshop.model.Car
import com.home.thecarshop.utilities.SELECTED_CAR_INTENT
import com.squareup.picasso.Picasso

class CarViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    fun bind(item: Car) {

        val photoImageView = view.findViewById<ImageView>(R.id.imageCar)
        Picasso.get().load(item.image).into(photoImageView)

        val name = view.findViewById<TextView>(R.id.txtTitle)
        name.text = item.name

        val price = view.findViewById<TextView>(R.id.txtPrice)
        price.text = item.price

        view.setOnClickListener {
            val bundle = bundleOf(SELECTED_CAR_INTENT to item)
            view.findNavController().navigate(R.id.carDetailFragment, bundle)
            println("CARSELECTED*** $item")

        }
    }

}