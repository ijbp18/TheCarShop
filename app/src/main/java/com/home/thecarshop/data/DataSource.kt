package com.home.thecarshop.data


import com.home.thecarshop.data.db.CarDto
import com.home.thecarshop.data.db.CategoryDto
import com.home.thecarshop.model.Car
import com.home.thecarshop.model.Category

interface DataSource {

    suspend fun cars(): OperationResult<List<Car>>
    suspend fun addCar(car: CarDto)
    suspend fun deleteCars()

    suspend fun category(): OperationResult<List<Category>>
    suspend fun addCategory(category: CategoryDto)




}