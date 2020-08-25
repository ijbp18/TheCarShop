package com.home.thecarshop.model

import com.home.thecarshop.utilities.IMAGE_URL
import java.io.Serializable

data class Car(
    var id: Int = 0,
    var name: String = "",
    val seat: String = "",
    var price: String = "",
    var state: Int = 0,
    val model: String = "",
    val released_date: String = "",
    val category: Int = 0,
    val conditionalCategory: String = "",
    var image: String = IMAGE_URL
): Serializable