package com.home.thecarshop.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.home.thecarshop.data.OperationResult

@Dao
interface CarDao {
    @Query("SELECT * FROM tb_car")
    suspend fun cars(): List<CarDto>

    @Insert
    suspend fun addCar(car: CarDto)

    @Query("DELETE FROM tb_car")
    suspend fun deleteAll()
}