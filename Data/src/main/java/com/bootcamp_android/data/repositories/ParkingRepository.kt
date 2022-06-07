package com.bootcamp_android.data.repositories

import com.bootcamp_android.domain.model.Parking
import com.bootcamp_android.domain.repostories.IParkingRepository
import com.bootcamp_android.domain.util.Utils

class ParkingRepository : IParkingRepository {

    private lateinit var lotRepository: LotRepository
    operator  fun invoke () = getParking()

    override fun getParking(): Parking = Parking(Utils.parkingId,lotRepository.getLots());
}