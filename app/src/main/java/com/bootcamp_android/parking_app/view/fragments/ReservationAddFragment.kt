package com.bootcamp_android.parking_app.view.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.parking_app.R
import com.bootcamp_android.parking_app.databinding.FragmentAddReservationBinding
import com.bootcamp_android.parking_app.utils.DateReservation
import com.bootcamp_android.parking_app.viewmodel.AddReservationViewModel
import com.bootcamp_android.parking_app.viewmodel.ViewModelFactory
import java.util.*

class ReservationAddFragment : Fragment() {

    private lateinit var addReservationViewModel: AddReservationViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private var binding: FragmentAddReservationBinding? = null

    //    private val args: ReservationAddFragmentArgs by navArgs()
    private var selectedLot = 1 // TODO check porque no toma el numero de lot seleccionado
    private var authorizationCode = ""
    private var initialDate = DateReservation()
    private var finalDate = DateReservation()
    private var spinnerList = mutableListOf<String>()

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
        addReservationViewModel.loadLots()
        addReservationViewModel.lots.observe(viewLifecycleOwner) { lots -> //            spinnerList.clear()
            lots.map {
                spinnerList.add("Lot: ${it.parkingLot}")
            }
        }
        binding?.apply {
            buttonSave.setOnClickListener {
                authorizationCode = textAuth.text.toString()
                val res = Reservation(
                    "",authorizationCode,initialDate.dateInMilliseconds,finalDate.dateInMilliseconds,selectedLot
                )
                addReservationViewModel.addReservation(res)

                addReservationViewModel.mutableSuccessfulAdd.observe(viewLifecycleOwner) { added ->
                    if(added) {
                        val action = ReservationAddFragmentDirections.actionFragmentAddReservationToLotListFragment()
                        findNavController().navigate(action)
                        Toast.makeText(context,"Added correctly",Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context,"Could not be processed",Toast.LENGTH_LONG).show()
                    }
                }
            }
            textSelectStartDateReservation.setOnClickListener {
                showDateTimePickerDialog(initialDate)
            }
            textSelectEndDateReservation.setOnClickListener {
                showDateTimePickerDialog(finalDate)
            }
            val adapter = ArrayAdapter(
                requireContext(),androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,spinnerList
            )


            lotsOptionsSpinner.apply {
                this.adapter = adapter
                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,view: View,position: Int,id: Long
                    ) {
                        Toast.makeText(
                            activity," " + "" + spinnerList[position],Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) { // write code to perform some action
                    }
                }
            }
        }
    }

    private fun showDateTimePickerDialog(date: DateReservation) {
        val calendar = Calendar.getInstance()
        val listenerHour = TimePickerDialog.OnTimeSetListener { _,hour,minutes ->
            calendar[Calendar.HOUR_OF_DAY] = hour
            calendar[Calendar.MINUTE] = minutes
            Toast.makeText(
                activity,"$hour:$minutes",Toast.LENGTH_LONG
            ).show()
            Log.d(TAG,"showDateTimePickerDialog: hour minutes $hour $minutes")
        }
        TimePickerDialog(
            activity,listenerHour,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false
        ).show()
        val dateTimeListener = DatePickerDialog.OnDateSetListener { _,year,month,day ->
            calendar[Calendar.YEAR] = year
            calendar[Calendar.MONTH] = month
            calendar[Calendar.DAY_OF_MONTH] = day
            Toast.makeText(
                activity,"$day/$month/$year",Toast.LENGTH_LONG
            ).show()
            Log.d(TAG,"showDateTimePickerDialog: dia mes anio : $day/$month/$year")
        }
        val datePicker = DatePickerDialog(
            requireContext(),
            dateTimeListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.datePicker.minDate = System.currentTimeMillis()
        datePicker.show()
        date.dateInMilliseconds = calendar.timeInMillis
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}

