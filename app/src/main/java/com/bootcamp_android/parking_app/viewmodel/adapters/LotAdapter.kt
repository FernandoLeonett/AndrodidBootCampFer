package com.bootcamp_android.parking_app.viewmodel.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp_android.parking_app.R
import com.codelab.domain.model.Lot

class LotAdapter(private val lotData: List<Lot>) : RecyclerView.Adapter<LotViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,viewType: Int
    ): LotViewHolder { // here i cannot get one view holder, that`s the reason i assign an custom int to the view type in getItemViewType
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_lot,parent,false)

        return LotViewHolder(view)
    }

    override fun onBindViewHolder(holder: LotViewHolder,position: Int) {
        val item = lotData[position]

        holder.bind(item)
    }

    override fun getItemCount() =
        lotData.size //ask for the type of the view and return it // statics methods for define view types i assign and int for each type so i can compare later
}
