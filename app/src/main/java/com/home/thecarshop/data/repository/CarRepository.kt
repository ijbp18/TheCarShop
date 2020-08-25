package com.home.thecarshop.data.repository

import com.home.thecarshop.data.OperationResult
import com.home.thecarshop.model.Car


interface CarRepository {
    suspend fun getCars(): OperationResult<List<Car>>
    suspend fun addCar(car: Car)
}