package com.home.thecarshop.utilities

import com.home.thecarshop.model.Car
import com.home.thecarshop.model.Category

class FormValidate {

    fun validateEmptyItem(item: Car): Boolean {
        return if(item.category > CATEGORY_BY_DEFAULT)
            item.category > 0 && item.state > -1 && item.name.isNotEmpty() && item.seat.isNotEmpty() && item.price.isNotEmpty() && item.model.isNotEmpty() && item.released_date.isNotEmpty()
        else
            item.category > 0 && item.state > -1 && item.name.isNotEmpty() && item.seat.isNotEmpty() && item.price.isNotEmpty() && item.model.isNotEmpty() && item.released_date.isNotEmpty()  && item.conditionalCategory.isNotEmpty()

    }

    fun validateEmptyCategory(item: Category): Boolean {
        println("*** Entro el VM validateEmptyCategory ")
        return item.name.isNotEmpty()

    }

    fun getConditionItem(idConditionSelected: Int): String {

        var description = ""
        for (index in CONDITION_DATA.indices) {
            if (index == idConditionSelected)
                description = CONDITION_DATA[index]

        }
        return description
    }


    fun getDescriptionItem(listCategories: List<Category>, idCategorySelected: Int): String {
        var description = ""
        listCategories.forEach {
            if (it.id == idCategorySelected) description = it.name
        }
        return description
    }

    fun getConditionItemCategory(idConditionSelected: Int): String {

        return when (idConditionSelected) {
            1 -> "Battery Capacity"
            2 -> "Available Payload"
            3 -> "Space Capacity"
            else -> "Observation"
        }

    }
}