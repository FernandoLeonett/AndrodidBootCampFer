package com.bootcamp_android.parking_app.viewmodel.adapters

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.model.LotDetail
import com.bootcamp_android.parking_app.databinding.FragmentLotBinding
import com.bootcamp_android.parking_app.utils.Services
import com.bootcamp_android.parking_app.utils.Services.fullDateFormatLot
import com.bootcamp_android.parking_app.utils.Services.timeFormatLot
import com.bootcamp_android.parking_app.utils.Utils


class LotViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    fun bind(lot: LotDetail,listener: (LotDetail) -> Unit) {
        val binding = FragmentLotBinding.bind(view)
        binding.apply {
            var hour = ""
            var date = Utils.free

            if(Services.isAvailableLot(lot.enDateDateTime,lot.startDateTime)) {
                date = fullDateFormatLot(lot.enDateDateTime)
                hour = timeFormatLot(lot.enDateDateTime)

            }

            textHourLot.text = hour
            textDateLot.text = date
            textIdLot.text = lot.parkingLot.toString()
            itemView.setOnClickListener {
//                Toast.makeText(view.context,"hola soy",Toast.LENGTH_SHORT).show()
                listener(lot)
            }

        }
    }
}

