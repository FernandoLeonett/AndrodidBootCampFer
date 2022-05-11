package com.bootcamp_android.parking_app.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.bootcamp_android.parking_app.R


class Adapter: RecyclerView.Adapter<DataViewHolder>() {

    private val adapterData = mutableListOf<DataModel>()
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): DataViewHolder {

        val layout = when (viewType) {
        TYPE_LOT-> R.layout.item_lot

            else -> throw IllegalArgumentException("Invalid type")
        }

        val view = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)

        return DataViewHolder(view)


    }

    override fun onBindViewHolder(holder: DataViewHolder,position: Int) {
       val item = adapterData[position]

        holder.binding(item)
    }

    override fun getItemCount()= adapterData.size

    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is DataModel.Lot -> TYPE_LOT
            else-> TYPE_RESERVATION

        }
    }

    fun setData(data: List<DataModel>) {
        adapterData.apply {
            clear()
            addAll(data)
        }
    }

    companion object {
        private const val TYPE_LOT = 0
        private const val TYPE_RESERVATION = 1

    }



}