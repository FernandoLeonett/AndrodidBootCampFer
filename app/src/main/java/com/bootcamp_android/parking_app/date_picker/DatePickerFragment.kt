package com.bootcamp_android.parking_app.date_picker

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.bootcamp_android.parking_app.R
import java.text.SimpleDateFormat
import java.util.*

class DatePickerFragment : DialogFragment(),DatePickerDialog.OnDateSetListener{

private val calendar = Calendar.getInstance()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val year =  calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(requireActivity(), R.style.mypickerDialog,this,year, month, day)// change style theme
    }
    override fun onDateSet(view: DatePicker?,year: Int,month: Int,dayOfMonth: Int) {
      calendar.set(Calendar.YEAR, year)
      calendar.set(Calendar.MONTH, month)
      calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        val selectedDate = SimpleDateFormat("dd-mm-yy", Locale.ENGLISH).format(calendar.time)
        val selectedDateBundle = Bundle()
        selectedDateBundle.putString("", selectedDate)
        setFragmentResult("REQUEST_CODE ",selectedDateBundle)
    }
}