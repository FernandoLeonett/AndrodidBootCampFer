package com.bootcamp_android.parking_app.adapter

import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp_android.parking_app.R
import java.util.Collections.addAll

class Adapter : RecyclerView.Adapter<DataViewHolder>() {
    //    private val adapterData = mutableListOf<DataModel>()
    private val adapterData = listOf(
        Lot("88","Thursday, 25 May,2022","8:30 pm",true),
        Lot("1","Monday, 25 May,2022","8:30 pm",false),
        Lot("1","Monday, 25 May,2022","8:30 pm",false),
        Lot("2","Monday, 25 May,2022","8:30 pm",false),
        Lot("3","Monday, 25 May,2022","8:30 pm",false),
        Lot("4","Monday, 25 May,2022","8:30 pm",false)
    )

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): DataViewHolder {
// here i cannot get one view holder, that`s the reason i assign an custom int to the view type in getItemViewType
        val layout = when (viewType) {
            TYPE_LOT -> R.layout.fragment_lot
            TYPE_RESERVATION -> R.layout.fragment_reservation
            else -> throw IllegalArgumentException("Invalid type")
        }
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layout,parent,false)

        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder,position: Int) {
        val item = adapterData[position]

        holder.binding(item)
    }

    override fun getItemCount() = adapterData.size

    //ask for the type of the view and return it
    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is Lot -> TYPE_LOT
            else -> TYPE_RESERVATION
        }
    }

//    fun setData(data: List<DataModel>) {
//        adapterData.apply {
//            clear()
//            addAll(data)
//        }
//    }

    // statics methods for define view types i assign and int for each type so i can compare later
    companion object {
        private const val TYPE_LOT = 0
        private const val TYPE_RESERVATION = 1
    }
}