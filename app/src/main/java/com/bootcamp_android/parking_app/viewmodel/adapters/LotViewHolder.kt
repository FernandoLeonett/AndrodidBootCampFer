package com.bootcamp_android.parking_app.viewmodel.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp_android.domain.model.LotDetail
import com.bootcamp_android.domain.util.Utils.fullDateFormatLot
import com.bootcamp_android.domain.util.Utils.isMarkedAsFree
import com.bootcamp_android.domain.util.Utils.timeFormatLot
import com.bootcamp_android.parking_app.databinding.FragmentLotBinding

import com.bootcamp_android.parking_app.utils.Utils


class LotViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    fun bind(lot: LotDetail,listener: (LotDetail) -> Unit) {
        val binding = FragmentLotBinding.bind(view)
        binding.apply {
            var hour = ""
            var date = Utils.free

            if(!isMarkedAsFree(lot.enDateDateTime,lot.startDateTime)) {
                date = fullDateFormatLot(lot.enDateDateTime)
                hour = timeFormatLot(lot.enDateDateTime)

            }

            textHourLot.text =  hour
            textDateLot.text = date
            textIdLot.text = lot.parkingLot.toString()
            itemView.setOnClickListener {
//                Toast.makeText(view.context,"start i",Toast.LENGTH_SHORT).show()
                listener(lot)
            }

        }
    }
}

