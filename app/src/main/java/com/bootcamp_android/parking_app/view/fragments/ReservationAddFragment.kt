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
import com.bootcamp_android.parking_app.R
import com.bootcamp_android.parking_app.databinding.FragmentAddReservationBinding
import java.util.*

class ReservationAddFragment : Fragment() {

    private var binding: FragmentAddReservationBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_add_reservation,container,false
        )
    }

    override fun onViewCreated(
        view: View,savedInstanceState: Bundle?
    ) {
        binding = FragmentAddReservationBinding.bind(view)

        binding?.apply {
            textSelectStartDateReservation.setOnClickListener {
                showDateTimePickerDialog()
            }
            textSelectEndDateReservation.setOnClickListener {
                showDateTimePickerDialog()
            }
            val spinnerList = listOf(
                "Parking Slot 1","Parking Slot 2","Parking Slot 3","Parking Slot 4","Parking Slot 5 final"
            ) //TODO("this is a test ready to receive arguments")
            val adapter = ArrayAdapter(
                requireContext(),androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,spinnerList
            )
            lotsOptionsSpinner.apply {
                this.adapter = adapter
                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,view: View?,position: Int,id: Long
                    ) {
                        Toast.makeText(
                            activity,"you have selected ${
                                parent?.getItemAtPosition(position).toString()
                            }",Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }
            }
        }
    }

    private fun showDateTimePickerDialog() {
        val cal = Calendar.getInstance()
        val listenerHora = TimePickerDialog.OnTimeSetListener { _,hour,minutes ->
            Toast.makeText(
                activity,"$hour:$minutes",Toast.LENGTH_LONG
            ).show()
            Log.d(
                TAG,"showDateTimePickerDialog: hour minutes $hour $minutes"
            )
        }

        TimePickerDialog(
            activity,listenerHora,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),false
        ).show()
        val dateTimeListener = DatePickerDialog.OnDateSetListener { _,year,month,day ->
            Toast.makeText(
                activity,"$day/$month/$year",Toast.LENGTH_LONG
            ).show()
            Log.d(
                TAG,"showDateTimePickerDialog: dia mes anio : $day/$month/$year"
            )
        }

        DatePickerDialog(
            requireContext(),
            dateTimeListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null;
    }
}

