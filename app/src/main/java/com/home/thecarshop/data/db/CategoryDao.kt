package com.home.thecarshop.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CategoryDao {
    @Query("SELECT * FROM tb_category")
    suspend fun categories(): List<CategoryDto>

    @Insert
    suspend fun addCategory(category: CategoryDto)

    @Query("DELETE FROM tb_category")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categories: List<CategoryDto>)
}