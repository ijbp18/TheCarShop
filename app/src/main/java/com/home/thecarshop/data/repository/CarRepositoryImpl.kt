package com.home.thecarshop.data.repository

import com.home.thecarshop.data.OperationResult
import com.home.thecarshop.data.db.CarDto
import com.home.thecarshop.data.DataSourceImpl
import com.home.thecarshop.model.Car

class CarRepositoryImpl(private val dataSource: DataSourceImpl): CarRepository {

    override suspend fun getCars(): OperationResult<List<Car>> {
        return dataSource.cars()
    }

    override suspend fun addCar(car:Car){
        dataSource.addCar(CarDto(car.id,car.name, car.seat, car.price, car.state, car.model, car.released_date, car.category, car.conditionalCategory, car.image))
    }

}