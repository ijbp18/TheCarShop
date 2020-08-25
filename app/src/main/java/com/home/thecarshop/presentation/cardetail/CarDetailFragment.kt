package com.home.thecarshop.presentation.cardetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import com.home.thecarshop.R
import com.home.thecarshop.data.OperationResult
import com.home.thecarshop.databinding.FragmentCarDetailBinding
import com.home.thecarshop.model.Car
import com.home.thecarshop.model.Category
import com.home.thecarshop.presentation.carlist.viewmodel.CarViewModel
import com.home.thecarshop.utilities.SELECTED_CAR_INTENT
import kotlinx.android.synthetic.main.fragment_car_detail.*
import org.koin.android.viewmodel.ext.android.sharedViewModel


class CarDetailFragment : Fragment() {

    lateinit var carSelected: Car
    private val viewModel: CarViewModel by sharedViewModel()
    lateinit var binding: FragmentCarDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCarDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getExtra()
        setupViewModel()
        loadInfo()
    }

    private fun setupViewModel() {
        viewModel.categorySel.observe(this, showCategoryDescription)
        viewModel.conditionSel.observe(this, showConditionDescription)
        viewModel.categories.observe(viewLifecycleOwner, showCategories)
    }

    private val showCategories = Observer<OperationResult<List<Category>>> { operation ->

        when (operation) {
            is OperationResult.Success -> {
                operation.data?.let { categories ->
                    println("***Selected categories From showCategories $categories")
                    if (categories.isNotEmpty())
                        carSelected.let { viewModel.loadDescriptionItems(it, categories) }
                    else {
                        Toast.makeText(
                            activity,
                            getString(R.string.problem_data_category),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }

            }
        }

    }

    private val showCategoryDescription = Observer<String> {
        binding.txtCategory.setText(it)
    }

    private val showConditionDescription = Observer<String> {
        binding.txtCondition.setText(it)
    }

    private fun loadInfo() {

        binding.carSelected = carSelected
        binding.toolbarLayout.title = carSelected.name

        when (carSelected.category) {
            1 -> txtConditionTitle.hint = getString(R.string.battery_capacity)
            2 -> txtConditionTitle.hint = getString(R.string.available_payload)
            3 -> txtConditionTitle.hint = getString(R.string.space_capacity)
            else -> lyt_condition_category.visibility = View.GONE
        }


        var isToolbarShown = false
        binding.detailScrollview.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
                val shouldShowToolbar = scrollY > toolbar.height

                if (isToolbarShown != shouldShowToolbar) {
                    isToolbarShown = shouldShowToolbar

                    appbar.isActivated = shouldShowToolbar
                    toolbar_layout.isTitleEnabled = shouldShowToolbar
                }
            }
        )
    }

    private fun getExtra() {
        carSelected = arguments?.getSerializable(SELECTED_CAR_INTENT) as Car
    }

}