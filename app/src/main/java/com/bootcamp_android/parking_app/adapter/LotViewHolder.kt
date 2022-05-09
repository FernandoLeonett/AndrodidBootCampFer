package com.bootcamp_android.parking_app.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp_android.parking_app.Lot
import com.bootcamp_android.parking_app.R

class LotViewHolder(view: View):RecyclerView.ViewHolder(view) {

    val id = view.findViewById<TextView>(R.id.text_id_lot)
    val date = view.findViewById<TextView>(R.id.text_date_lot)
    val hour = view.findViewById<TextView>(R.id.text_hour_lot)


    fun binding(lotModel : Lot){

        id.text = lotModel.id
        date.text = lotModel.date

        hour.text = lotModel.hour




    }
}