package com.bootcamp_android.data.repositories

import com.bootcamp_android.data.services.ParkingService
import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.model.Parking
import com.bootcamp_android.domain.repostories.ILotsRepository
import com.bootcamp_android.domain.util.Result
import com.bootcamp_android.domain.util.Utils

class LotRepository : ILotsRepository {

    override suspend fun getDetailLot(id: Int) = getLots().find { it.id == id }

      var lotService: ParkingService = ParkingService()

    //bring list from the service
    override suspend fun getLots(): List<Lot> {
        var lotList = mutableListOf<Lot>()
        var result = lotService.getLots()
        var resultLotList = Parking(Utils.parkingId,lotList)

        if(result is Result.Success) {
            result.data.forEach {
                lotList.add(Lot(it.parkingLot))
            }
        }
        resultLotList.lotsForReserve = lotList

        return resultLotList.lotsForReserve
    }
}
