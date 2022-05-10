package com.bootcamp_android.parking_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp_android.parking_app.Lot
import com.bootcamp_android.parking_app.R

class LotAdapter(val lotList: List<Lot>): RecyclerView.Adapter<LotViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): LotViewHolder {
 val layoutInflater = LayoutInflater.from(parent.context)

        return LotViewHolder( layoutInflater.inflate(R.layout.item_lot, parent,false))
    }

    override fun onBindViewHolder(holder: LotViewHolder,position: Int) {
       val item = lotList[position]

        holder.binding(item)
    }

    override fun getItemCount()= lotList.size

}