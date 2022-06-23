package com.bootcamp_android.domain.usecases

import com.bootcamp_android.domain.model.Lot

class CalculateFreeLotsUseCase {

     operator fun invoke(lots: List<Lot>) = geNumberOfFreeLots(lots)
    private  fun geNumberOfFreeLots(lots: List<Lot>): Int = lots.count {
        it.dateTimeAvailable == -1L
    }
}