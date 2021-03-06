package com.bootcamp_android.parking_app.view.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.util.AddResult
import com.bootcamp_android.parking_app.R
import com.bootcamp_android.parking_app.databinding.FragmentAddReservationBinding
import com.bootcamp_android.parking_app.utils.DateReservation
import com.bootcamp_android.parking_app.utils.Utils
import com.bootcamp_android.parking_app.viewmodel.AddReservationViewModel
import com.bootcamp_android.parking_app.viewmodel.ViewModelFactory
import java.util.*

class ReservationAddFragment : Fragment() {

    private lateinit var addReservationViewModel: AddReservationViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private var binding: FragmentAddReservationBinding? = null
    private var selectedLot: Any = -1
    private var authorizationCode = ""
    private var initialDate = DateReservation()
    private var finalDate = DateReservation()
    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
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
                    "",authorizationCode,initialDate.dateInMilliseconds,finalDate.dateInMilliseconds,selectedLot as Int
                )
                val ok: AddResult.AddRequest = addReservationViewModel.validateDataUser(
                    res
                )
                if(!ok.succes) {
                    errorMessageAdd(ok)
                } else {
                    addReservationViewModel.addReservation(res)
                    addReservationViewModel.successfulAdded.observe(viewLifecycleOwner) { result ->
                        if(result == AddResult.AddResponse.SUCCESS) {
                            successAddMessage()
                        } else {
                            errorMessageAdd(result)
                        }
                        addReservationViewModel.successfulAdded = MutableLiveData<AddResult>()
                    }
                }
            }
            textSelectStartDateReservation.setOnClickListener {
                showDateTimePickerDialog(initialDate,0)
            }
            textSelectEndDateReservation.setOnClickListener {
                showDateTimePickerDialog(finalDate,1)
            }

            lotsOptionsSpinner.apply {
                addReservationViewModel.loadLots()
                val adapter = ArrayAdapter<Any>(
                    requireContext(),androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
                )

                addReservationViewModel.lots.observe(viewLifecycleOwner) { lots ->
                    adapter.add(Utils.spinnerDefaultValue)
                    lots.forEach {
                        adapter.add(Utils.PLACEHOLDER_LOT + it.parkingLot)
                    }
                }
                this.adapter = adapter
                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,view: View,position: Int,id: Long
                    ) {
                        if(parent.getItemAtPosition(position) != Utils.spinnerDefaultValue) {
                            selectedLot =
                                (parent.getItemAtPosition(position) as String).replace(Utils.PLACEHOLDER_LOT,"").toInt()
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                    }
                }
            }
        }
    }

    private fun errorMessageAdd(ok: AddResult) {
        var msg = ""
        when(ok) {
            is AddResult.AddRequest -> {
                if(!ok.endValid) {
                    msg += "\n" + getString(R.string.msg_reservation_add_error_end_date)
                }
                if(!ok.startValid) {
                    msg += "\n" + getString(R.string.msg_reservation_add_error_start_date)
                }
                if(!ok.parkingValid) {
                    msg += "\n" + getString(R.string.msg_reservation_add_error_lot)
                }
                if(!ok.authValid) {
                    msg += "\n" + getString(R.string.msg_reservation_add_error_auth)
                }
            }
            else -> msg = when(ok) {
                AddResult.AddResponse.ORDER -> getString(R.string.msg_reservation_add_error_order_date)
                AddResult.AddResponse.ORDER -> getString(R.string.msg_lot_is_busy)
                AddResult.AddResponse.REQUEST -> getString(R.string.msg_add_bad_request)
                else -> getString(R.string.msg_server_error)
            }
        }


        AlertDialog.Builder(requireContext()).apply {
            setTitle(getString(R.string.msg_reservation_title_error))
            setMessage(msg).setPositiveButton(getString(R.string.text_btn_delete_positive)) { dialogInterface,_ ->
                dialogInterface.dismiss()
            }.show()
        }
    }

    private fun successAddMessage() {
        binding?.apply {
            textSelectEndDateReservation.text = ""
            textSelectStartDateReservation.text = ""
            textAuth.text.clear()
        }
        AlertDialog.Builder(requireContext()).apply {
            setTitle(getString(R.string.msg_reservation_add_success))
            setMessage(R.string.msg_continue_adding).setPositiveButton(getString(R.string.text_btn_add_positive)) { dialogInterface,_ ->
                dialogInterface.cancel()
            }.setNegativeButton(getString(R.string.text_btn_add_negative)) { dialogInterface,_ ->
                dialogInterface.dismiss()

                findNavController().popBackStack()
            }
            show()
        }
    }

    private fun showDateTimePickerDialog(date: DateReservation,inputDate: Int) {
        val calendar = Calendar.getInstance()
        val listenerHour = TimePickerDialog.OnTimeSetListener { _,hour,minutes ->
            calendar[Calendar.HOUR_OF_DAY] = hour
            calendar[Calendar.MINUTE] = minutes
            val input = when(inputDate) {
                0 -> binding?.textSelectStartDateReservation
                else -> binding?.textSelectEndDateReservation
            }
            input?.text = Utils.formatDateInput(calendar.timeInMillis)
            date.dateInMilliseconds = calendar.timeInMillis
        }
        TimePickerDialog(
            activity,listenerHour,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false
        ).show()
        val dateTimeListener = DatePickerDialog.OnDateSetListener { _,year,month,day ->
            calendar[Calendar.YEAR] = year
            calendar[Calendar.MONTH] = month
            calendar[Calendar.DAY_OF_MONTH] = day
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
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}

