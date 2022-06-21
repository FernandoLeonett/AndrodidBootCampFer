package com.bootcamp_android.parking_app.viewmodel.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.parking_app.R

class ReservationsAdapter(
    private val reservationData: MutableList<Reservation>,private var listener: (Reservation,Int) -> Unit
) : RecyclerView.Adapter<ReservationViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,viewType: Int
    ): ReservationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_reservation,parent,false)
        return ReservationViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReservationViewHolder,position: Int) {
        val item = reservationData[position]
        holder.bind(item,listener)
    }

    override fun getItemCount() = reservationData.size
}


