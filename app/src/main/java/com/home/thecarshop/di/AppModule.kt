package com.home.thecarshop.di


import com.home.thecarshop.data.DataSourceImpl
import com.home.thecarshop.data.repository.CarRepository
import com.home.thecarshop.data.repository.CarRepositoryImpl
import com.home.thecarshop.data.repository.CategoryRepository
import com.home.thecarshop.data.repository.CategoryRepositoryImpl
import com.home.thecarshop.presentation.addnewcar.viewmodel.AddCarViewModel
import com.home.thecarshop.presentation.carlist.viewmodel.CarViewModel
import com.home.thecarshop.utilities.FormValidate
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CarViewModel(dbRepository = get(), dbCategoryRepository = get(), formValidate = get()) }
    viewModel { AddCarViewModel(dbRepository = get(), categoryRepository = get(), formValidate = get()) }
}

val dataSourceModule = module {
    single { DataSourceImpl(context = get()) }
}

val repositoryModule = module {
    single {
        CarRepositoryImpl(
            dataSource = get()
        ) as CarRepository
    }

    single {
        CategoryRepositoryImpl(
            dataSource = get()
        ) as CategoryRepository
    }
}

val formValidateModule = module {
    single { FormValidate() }
}

val appModule = listOf(
    repositoryModule, viewModelModule, dataSourceModule, formValidateModule
)

