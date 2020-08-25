package com.home.thecarshop.presentation.carlist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.home.thecarshop.R
import com.home.thecarshop.data.OperationResult
import com.home.thecarshop.model.Car
import com.home.thecarshop.model.Category
import com.home.thecarshop.presentation.carlist.adapter.CarListAdapter
import com.home.thecarshop.presentation.carlist.viewmodel.CarViewModel
import com.home.thecarshop.utilities.AUXILIAR_CODE
import kotlinx.android.synthetic.main.bottom_sheet_layout.*
import kotlinx.android.synthetic.main.fragment_car_list.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class CarListFragment : Fragment(), View.OnClickListener{

    private val viewModel: CarViewModel by sharedViewModel()
    private val carsAdapter: CarListAdapter = CarListAdapter()
    private lateinit var bottomSheetDialog : BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_car_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        initUI()
        viewModel.retrieveCars()
        setHasOptionsMenu(true)

        fab.setOnClickListener {
            findNavController().navigate(R.id.addCarCategoryFragment)
        }
    }

    private fun setupViewModel() {

        viewModel.cars.observe(viewLifecycleOwner, Observer { operation ->
            when(operation){
                is OperationResult.Loading -> {
                    progressBarHome.visibility = View.VISIBLE
                }

                is OperationResult.Success -> {
                    operation.data?.let { cars ->
                        if (cars.isNotEmpty()) {
                            progressBarHome.visibility = View.GONE
                            lyt_empty.visibility = View.GONE
                            showCars(cars)
                        } else {
                            progressBarHome.visibility = View.GONE
                            lyt_empty.visibility = View.VISIBLE
                            lyt_home.visibility = View.GONE
                        }
                    }
                }
            }
        })

//        viewModel.isSaveSuccessfully.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
//            if(it) {
//                Toast.makeText(activity, getString(R.string.category_added_txt), Toast.LENGTH_SHORT).show()
//                if(bottomSheetDialog.isShowing) bottomSheetDialog.dismiss()
//            }else Toast.makeText(activity, getString(R.string.empty_field_category_name), Toast.LENGTH_SHORT).show()
//
//        })
    }

    private fun showCars(cars: List<Car>) {
        carsAdapter.setData(cars)
    }

    private fun initUI() {
        recycler_car.adapter = carsAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home_settings, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                showBottomSheetDialog()
                true
            }
            else -> {super.onOptionsItemSelected(item)
                false
            }
        }
    }

    private fun showBottomSheetDialog() {
        bottomSheetDialog = BottomSheetDialog(requireActivity())
        val view = layoutInflater.inflate(R.layout.bottom_sheet_layout,null)
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
        bottomSheetDialog.btnSaveCategory.setOnClickListener (this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.btnSaveCategory -> {
                viewModel.saveCategory(Category(AUXILIAR_CODE, bottomSheetDialog.txtCategoryName.text.toString()))

                viewModel.isSaveSuccessfully.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                    if(it) {
                        Toast.makeText(activity, getString(R.string.category_added_txt), Toast.LENGTH_SHORT).show()
                        if(bottomSheetDialog.isShowing) bottomSheetDialog.dismiss()
                    }else Toast.makeText(activity, getString(R.string.empty_field_category_name), Toast.LENGTH_SHORT).show()

                })
            }
        }
    }

}