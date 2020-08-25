package com.home.thecarshop.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import com.home.thecarshop.model.Car
import com.home.thecarshop.model.Category

@Entity(tableName = "tb_category")
data class CategoryDto(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")val id: Int,
    @ColumnInfo(name = "description") val name: String
)

fun CategoryDto.toDomain() = Category(
    id = id,
    name = name
)