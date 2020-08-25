package com.home.thecarshop.presentation.addnewcar

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.home.thecarshop.R
import com.home.thecarshop.model.Car
import com.home.thecarshop.model.Category
import com.home.thecarshop.model.Condition
import com.home.thecarshop.presentation.addnewcar.adapter.ConditionListAdapter
import com.home.thecarshop.presentation.addnewcar.viewmodel.AddCarViewModel
import com.home.thecarshop.utilities.CATEGORY_BY_DEFAULT
import com.home.thecarshop.utilities.OnItemSelected
import kotlinx.android.synthetic.main.fragment_add_car.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import java.util.*


class AddCarStepInfoFragment : Fragment(),  DatePickerDialog.OnDateSetListener {

    private var selectedCondition: Condition? = null
    private var selectedCategory: Category? = null
    private var isDateReleased = false

    private val viewModel: AddCarViewModel by sharedViewModel()

    private val onItemSelectedListener: OnItemSelected<Condition> =
        object :
            OnItemSelected<Condition> {
            override fun onItemSelected(item: Condition) {
                selectedCondition = item
                item.isSelected = true
                conditionAdapter.unSelectAllDistinctTo(item)
                conditionAdapter.notifyDataSetChanged()

            }
        }

    private val conditionAdapter: ConditionListAdapter =
        ConditionListAdapter(
            onItemSelectedListener
        )

    private var day = 0
    private var month = 0
    private var year = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_car, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getCategorySelected()
    }

    private fun getCategorySelected() {
        viewModel.selectedCategory.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            selectedCategory = it
            if(it.id > CATEGORY_BY_DEFAULT){
                lytConditional.visibility = View.GONE
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        selectedCondition = Condition(-1, "N/A")

        conditionAdapter.setData(listOf(Condition(0, "New", false), Condition(1, "Used", false)))
        recycler_condition.adapter = conditionAdapter

        txtModel.setOnClickListener {
            isDateReleased = false
            getDateDialog()
        }

        txtDate.setOnClickListener {
            isDateReleased = true
            getDateDialog()
        }

        btnSaveCar.setOnClickListener {
            viewModel.saveCar(
                Car(
                    0,
                    txtTitle.text.toString(),
                    txtSeat.text.toString(),
                    txtPrice.text.toString(),
                    selectedCondition!!.id,
                    txtModel.text.toString(),
                    txtDate.text.toString(),
                    selectedCategory!!.id,
                    txtConditional.text.toString()
                )
            )

            viewModel.isSaveSuccessfully.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                if(it) findNavController().navigate(R.id.carListFragment)
                else Toast.makeText(activity, getString(R.string.empty_fields_message), Toast.LENGTH_SHORT).show()
            })
        }

    }

    private fun setupViewModel() {


        viewModel.conditionalText.observe(viewLifecycleOwner, androidx.lifecycle.Observer { conditional ->
            txtInputConditional.hint = conditional
        })
    }


    private fun getDateDialog() {
        getDayTimeCalendar()
        context?.let { DatePickerDialog(it, this, year, month, day).show() }
    }

    private fun getDayTimeCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    override fun onDateSet(view: DatePicker?, mYear: Int, mMonth: Int, mDayOfMonth: Int) {

        val strMont = if (mMonth < 10) "0$mMonth" else mMonth.toString()
        val strDay = if (mDayOfMonth < 10) "0$mDayOfMonth" else mDayOfMonth.toString()

        if (isDateReleased)
            txtDate.setText(getString(R.string.date_text, strDay, strMont, mYear))
        else
            txtModel.setText(getString(R.string.only_year_text, mYear))
    }

}