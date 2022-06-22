package com.bootcamp_android.parking_app.viewmodel.adapters

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.parking_app.databinding.FragmentLotBinding
import com.bootcamp_android.parking_app.utils.Utils
import com.bootcamp_android.parking_app.utils.Utils.formatDateForLotList
import com.bootcamp_android.parking_app.utils.Utils.timeAvailable
import com.bootcamp_android.parking_app.utils.Utils.timeFormat

class LotViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    fun bind(lot: Lot,listener: (Int) -> Unit) {
        val binding = FragmentLotBinding.bind(view)

        binding.apply {
            if(lot.free) {
                itemView.setBackgroundColor(Color.parseColor(Utils.FREE_COLOR))
                textHourLot.text = ""
                textDateLot.text = Utils.FREE
            } else {
                textHourLot.text = formatDateForLotList(lot.dateTimeAvailable)
                textDateLot.text = timeFormat(lot.dateTimeAvailable)
                itemView.setBackgroundColor(
                    Color.parseColor(Utils.BUSY_COLOR)
                )
            }


            textIdLot.text = lot.parkingLot.toString()
            timeAvailable.text = timeAvailable(lot.availableForTime)
            itemView.setOnClickListener {
                listener(lot.parkingLot)
            }
        }
    }
}

