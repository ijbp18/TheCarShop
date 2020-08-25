package com.home.thecarshop.presentation.addnewcar.viewmodel

import androidx.lifecycle.*
import com.home.thecarshop.data.OperationResult
import com.home.thecarshop.data.repository.CarRepository
import com.home.thecarshop.data.repository.CategoryRepository
import com.home.thecarshop.model.Car
import com.home.thecarshop.model.Category
import com.home.thecarshop.utilities.FormValidate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddCarViewModel(private val dbRepository: CarRepository, private val categoryRepository: CategoryRepository, private val formValidate : FormValidate) : ViewModel() {

    val titleText = MutableLiveData("")
    val seatText = MutableLiveData("")
    val priceText = MutableLiveData("")
    val dateText = MutableLiveData("")
    val modelText = MutableLiveData("")
    val conditionalText = MutableLiveData("")


    private val _isSaveSuccessfully = MutableLiveData<Boolean>()
    val isSaveSuccessfully: LiveData<Boolean> get() = _isSaveSuccessfully

    private val _categories = MutableLiveData<OperationResult<List<Category>>>()
    val categories: LiveData<OperationResult<List<Category>>> = _categories

    private var _selectedCategory = MutableLiveData<Category>()
    val selectedCategory: LiveData<Category> = _selectedCategory


//    init {
//        retrieveCategories()
//    }

    fun retrieveCategories() {
        viewModelScope.launch {
            _categories.value = with(Dispatchers.IO) { categoryRepository.getCategories() }
        }
    }

    fun saveCar(car: Car) {
        if(formValidate.validateEmptyItem(car)) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    dbRepository.addCar(car)
                }
            }
            _isSaveSuccessfully.value = true
        }else{
            _isSaveSuccessfully.value =  false
        }
    }

    fun setSelectedCategory(item: Category) {
        _selectedCategory.value = item
        conditionalText.value = formValidate.getConditionItemCategory(item.id)
    }

}