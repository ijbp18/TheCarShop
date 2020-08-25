package com.home.thecarshop.presentation.addnewcar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.home.thecarshop.data.repository.CarRepositoryImpl
import com.home.thecarshop.data.repository.CategoryRepository
import com.home.thecarshop.utilities.FormValidate

class ViewModelFactory(private val dbRepository: CarRepositoryImpl, private val dbCategoryRepository: CategoryRepository, private val formValidation: FormValidate):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddCarViewModel(
            dbRepository,
            dbCategoryRepository,
            formValidation
        ) as T
    }
}