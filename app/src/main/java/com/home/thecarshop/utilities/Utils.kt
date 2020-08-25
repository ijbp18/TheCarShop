package com.home.thecarshop.utilities

import java.text.DecimalFormat
import java.text.NumberFormat

object Utils {

    fun numberFormat(number: String): String{
        val formatter: NumberFormat = DecimalFormat("#,###")
        return formatter.format(number)
    }



}