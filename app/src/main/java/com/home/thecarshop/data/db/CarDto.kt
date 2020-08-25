package com.home.thecarshop.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import com.home.thecarshop.model.Car

@Entity(tableName = "tb_car")
data class CarDto(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "seat") val seat: String,
    @ColumnInfo(name = "price") var price: String,
    @ColumnInfo(name = "state") var state: Int,
    @ColumnInfo(name = "model") val model: String,
    @ColumnInfo(name = "released_date") val released_date: String,
    @ColumnInfo(name = "category") val category: Int,
    @ColumnInfo(name = "category_conditional") val categoryConditional: String,
    @ColumnInfo(name = "image") val image: String
)

fun CarDto.toDomain() = Car(
    id = id,
    name = name,
    seat = seat,
    price = price,
    state = state,
    model = model,
    released_date = released_date,
    category = category,
    conditionalCategory = categoryConditional,
    image = image
)