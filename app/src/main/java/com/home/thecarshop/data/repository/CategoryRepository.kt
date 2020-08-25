package com.home.thecarshop.data.repository

import com.home.thecarshop.data.OperationResult
import com.home.thecarshop.model.Car
import com.home.thecarshop.model.Category


interface CategoryRepository {
    suspend fun getCategories(): OperationResult<List<Category>>
    suspend fun addCategory(category: Category)
}