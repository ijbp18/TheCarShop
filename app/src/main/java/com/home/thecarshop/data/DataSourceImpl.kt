package com.home.thecarshop.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.home.thecarshop.data.db.*
import com.home.thecarshop.model.Car
import com.home.thecarshop.model.Category

class DataSourceImpl(context: Context) : DataSource {

    private lateinit var carDao: CarDao
    private lateinit var categoryDao: CategoryDao

    init {
        val db = AppDataBase.getInstance(context)
        db.let {
            carDao = it.carDao()
            categoryDao = it.categoryDao()
        }
    }

    override suspend fun cars(): OperationResult<List<Car>> {
        val carlist =  carDao.cars()
        carlist.let {cars ->
            return if(cars.isNotEmpty()){
                OperationResult.Success(cars.map { carDto ->  carDto.toDomain() })
            }else
                OperationResult.Success(emptyList())

        }

    }

    override suspend fun addCar(car: CarDto) {
        return carDao.addCar(car)
    }

    override suspend fun deleteCars() {
        return carDao.deleteAll()
    }

    override suspend fun category(): OperationResult<List<Category>> {
        val categories =  categoryDao.categories()
        categories.let {categoryList ->
            return if(categoryList.isNotEmpty()){
                OperationResult.Success(categoryList.map { categoryDto ->  categoryDto.toDomain() })
            }else
                OperationResult.Success(emptyList())

        }
    }

    override suspend fun addCategory(category: CategoryDto) {
       return categoryDao.addCategory(category)
    }


}