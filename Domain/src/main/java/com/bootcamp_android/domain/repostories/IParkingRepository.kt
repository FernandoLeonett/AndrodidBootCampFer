package com.bootcamp_android.domain.repostories;

import com.bootcamp_android.domain.model.Lot;
import com.bootcamp_android.domain.model.Parking

interface IParkingRepository {

    fun getParking(): Parking
}
