package com.bootcamp_android.parking_app.view.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.parking_app.R
import com.bootcamp_android.parking_app.databinding.FragmentAddReservationBinding
import com.bootcamp_android.parking_app.viewmodel.AddReservationViewModel
import com.bootcamp_android.parking_app.viewmodel.ViewModelFactory
import java.util.*

class ReservationAddFragment : Fragment() {

    private lateinit var addReservationViewModel: AddReservationViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private var binding: FragmentAddReservationBinding? = null
    private val args: ReservationAddFragmentArgs by navArgs()
    private var startDate: Long = -1
    private var endDate: Long = -1
    private var selectedLot = -1
    private var authorizationCode = ""

    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        viewModelFactory = ViewModelFactory(requireContext())
        addReservationViewModel = ViewModelProvider(
            this,viewModelFactory
        ).get(AddReservationViewModel::class.java)
        return inflater.inflate(
            R.layout.fragment_add_reservation,container,false
        )
    }

    override fun onViewCreated(
        view: View,savedInstanceState: Bundle?
    ) {
        binding = FragmentAddReservationBinding.bind(view)

        binding?.apply {
            buttonSave.setOnClickListener {
                authorizationCode = textAuth.text.toString()
                val res = Reservation(
                    "",authorizationCode,startDate,endDate,selectedLot
                )

                addReservationViewModel.mutableSuccessfulAdd.observe(viewLifecycleOwner) {
                    var msg = if(it) "Reservation Added Successfully" else "Error Please Chek the reservation"

                    Toast.makeText(activity,msg,Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_fragmentAddReservation_to_lotListFragment)
                }
                addReservationViewModel.addReservation(res)
            }
            textSelectStartDateReservation.setOnClickListener {
                startDate = showDateTimePickerDialog()
            }
            textSelectEndDateReservation.setOnClickListener {
                endDate = showDateTimePickerDialog()
            }
            var spinnerList: List<String> = args.lots.toList()
            val adapter = ArrayAdapter(
                requireContext(),androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,spinnerList
            )


            lotsOptionsSpinner.apply {
                this.adapter = adapter
                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,view: View?,position: Int,id: Long
                    ) {
                        selectedLot = position
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }
            }
        }
    }

    private fun showDateTimePickerDialog(): Long {
        var hourSelected: Int = 0;
        var minSelected = 0;
        var yearSelected = 0;
        var monthSelected = 0;
        var daySelected = 0
        val cal = Calendar.getInstance()
        val listenerHora = TimePickerDialog.OnTimeSetListener { _,hour,minutes ->
            hourSelected = hour
            minSelected = minutes
        }


        TimePickerDialog(
            activity,listenerHora,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),false
        ).show()
        val dateTimeListener = DatePickerDialog.OnDateSetListener { _,year,month,day ->
            yearSelected = year;
            monthSelected = month
            daySelected = day
        }
        val date = DatePickerDialog(
            requireContext(),
            dateTimeListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )
        date.datePicker.minDate = System.currentTimeMillis()
        date.show()
        return getTimeInMillis(daySelected,monthSelected,yearSelected,hourSelected,minSelected)
    }

    private fun getTimeInMillis(day: Int,month: Int,year: Int,hour: Int,min: Int): Long {
        val calendar = Calendar.getInstance()
        calendar[year,month] = day
        var time = calendar.timeInMillis + hour * 3600000 + min * 60000
        if(time < System.currentTimeMillis()) {
            time = -1
        }
        return time
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null;
    }
}

