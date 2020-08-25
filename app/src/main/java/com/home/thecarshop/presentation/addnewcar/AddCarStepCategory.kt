package com.home.thecarshop.presentation.addnewcar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.home.thecarshop.R
import com.home.thecarshop.data.OperationResult
import com.home.thecarshop.model.Category
import com.home.thecarshop.presentation.addnewcar.adapter.CategoryListAdapter
import com.home.thecarshop.presentation.addnewcar.viewmodel.AddCarViewModel
import com.home.thecarshop.utilities.OnItemSelected
import kotlinx.android.synthetic.main.fragment_add_car_category.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class AddCarStepCategory : Fragment() {

    private val viewModel: AddCarViewModel by sharedViewModel()

    private val onItemSelectedListener: OnItemSelected<Category> =
        object : OnItemSelected<Category> {
            override fun onItemSelected(item: Category) {
                viewModel.setSelectedCategory(item)
                findNavController().navigate(R.id.addCarFragment)
            }
        }

    private val categoriesAdapter: CategoryListAdapter = CategoryListAdapter(onItemSelectedListener)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_car_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        initUI()
    }

    private fun setupViewModel() {
        viewModel.categories.observe(viewLifecycleOwner, Observer { operation ->
            when(operation){
                is OperationResult.Loading -> {
                    progressBarCategory.visibility = View.VISIBLE
                }

                is OperationResult.Success -> {
                    operation.data?.let { categories ->
                        progressBarCategory.visibility = View.GONE
                        if (categories.isNotEmpty()) {
                            showCategories(categories)
                        }
                    }
                }
            }
        })
    }

    private fun showCategories(categories: List<Category>) {
        categoriesAdapter.setData(categories)
    }

    private fun initUI() {
        rv_category.adapter = categoriesAdapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.retrieveCategories()
    }

}