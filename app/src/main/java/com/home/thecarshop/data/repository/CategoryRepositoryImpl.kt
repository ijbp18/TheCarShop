package com.home.thecarshop.data.repository

import com.home.thecarshop.data.OperationResult
import com.home.thecarshop.data.db.CarDto
import com.home.thecarshop.data.DataSourceImpl
import com.home.thecarshop.data.db.CategoryDto
import com.home.thecarshop.model.Car
import com.home.thecarshop.model.Category

class CategoryRepositoryImpl(private val dataSource: DataSourceImpl): CategoryRepository {

    override suspend fun getCategories(): OperationResult<List<Category>> {
        return dataSource.category()
    }

    override suspend fun addCategory(category: Category) {
        dataSource.addCategory(CategoryDto(category.id, category.name))
    }

}