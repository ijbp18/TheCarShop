package com.home.thecarshop.presentation.carlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.home.thecarshop.data.OperationResult
import com.home.thecarshop.data.repository.CarRepository
import com.home.thecarshop.data.repository.CategoryRepository
import com.home.thecarshop.model.Car
import com.home.thecarshop.model.Category
import com.home.thecarshop.utilities.FormValidate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CarViewModel(private val dbRepository: CarRepository, private val dbCategoryRepository: CategoryRepository, private val formValidate: FormValidate) : ViewModel() {

    private val _categorySel = MutableLiveData<String>()
    val categorySel: LiveData<String> get() = _categorySel

    private val _conditionSel = MutableLiveData<String>()
    val conditionSel: LiveData<String> get() = _conditionSel

    private val _categories = MutableLiveData<OperationResult<List<Category>>>()
    val categories: LiveData<OperationResult<List<Category>>> get() = _categories

    private val _cars = MutableLiveData<OperationResult<List<Car>>>()
    val cars: LiveData<OperationResult<List<Car>>> = _cars

    private val _isSaveSuccessfully = MutableLiveData<Boolean>()
    val isSaveSuccessfully: LiveData<Boolean> get() = _isSaveSuccessfully

    init {
        retrieveCategories()
    }

    private fun retrieveCategories() {
        viewModelScope.launch {
            _categories.value  = with(Dispatchers.IO) {dbCategoryRepository.getCategories()}
        }
    }

    fun loadDescriptionItems(carSelected: Car, list: List<Category>) {
        _categorySel.value = formValidate.getDescriptionItem(list, carSelected.category)
        _conditionSel.value = formValidate.getConditionItem(carSelected.state)
    }

    fun retrieveCars() {
        _cars.value = OperationResult.Loading()
        viewModelScope.launch {
            _cars.value = with(Dispatchers.IO) { dbRepository.getCars() }
        }
    }

    fun saveCategory(category: Category){

        println("*** Entro el VM saveCategory ")

        if(formValidate.validateEmptyCategory(category)) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    println("*** Entro el VM viewModelScope ")
                    dbCategoryRepository.addCategory(category)
                }
            }
            _isSaveSuccessfully.value = true
        }else{
            println("*** Entro en el Else de validateEmptyCategory ")
            _isSaveSuccessfully.value =  false
        }


    }


}