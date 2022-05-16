package com.bootcamp_android.parking_app.adapter_spinner

import android.app.Activity
import android.view.View
import android.widget.AdapterView

class SpinnerActivity:Activity(), AdapterView.OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>?,view: View?,position: Int,id: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}