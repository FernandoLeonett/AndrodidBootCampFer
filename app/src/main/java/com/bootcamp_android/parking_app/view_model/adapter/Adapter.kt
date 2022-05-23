package com.bootcamp_android.parking_app.view_model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp_android.parking_app.R
import com.bootcamp_android.parking_app.model.DataModel
import com.bootcamp_android.parking_app.model.Lot

class Adapter(private val adapterData: List<DataModel>) : RecyclerView.Adapter<DataViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,viewType: Int
    ): DataViewHolder { // here i cannot get one view holder, that`s the reason i assign an custom int to the view type in getItemViewType
        val layout = when(viewType) {
            TYPE_LOT -> R.layout.fragment_lot
            TYPE_RESERVATION -> R.layout.fragment_reservation
            else -> throw IllegalArgumentException("Invalid type")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout,parent,false)

        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder,position: Int) {
        val item = adapterData[position]

        holder.binding(item)
    }

    override fun getItemCount() = adapterData.size

    //ask for the type of the view and return it
    override fun getItemViewType(position: Int): Int {
        return when(adapterData[position]) {
            is Lot -> TYPE_LOT
            else -> TYPE_RESERVATION
        }
    }

    // statics methods for define view types i assign and int for each type so i can compare later
    companion object {

        private const val TYPE_LOT = 0
        private const val TYPE_RESERVATION = 1
    }
}