package com.bootcamp_android.parking_app.viewmodel.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp_android.domain.model.LotDetail
import com.bootcamp_android.parking_app.R

class ReservationAdapter(private val reservationData: List<LotDetail>) :
    RecyclerView.Adapter<ReservationViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,viewType: Int
    ): ReservationViewHolder { //
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_reservation,parent,false)

        return ReservationViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReservationViewHolder,position: Int) {
        val item = reservationData[position]

        holder.bind(item)
    }

    override fun getItemCount() =
        reservationData.size //ask for the type of the view and return it // statics methods for define view types i assign and int for each type so i can compare later
}
