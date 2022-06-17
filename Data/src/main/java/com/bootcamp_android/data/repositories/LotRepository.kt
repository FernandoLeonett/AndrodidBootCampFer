package com.bootcamp_android.data.repositories

import com.bootcamp_android.data.room.localdatabase.ParkingDataBase
import com.bootcamp_android.data.room.mapper.LotMapperLocal
import com.bootcamp_android.data.services.ParkingService
import com.bootcamp_android.data.services.response.LotResponse
import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.model.Parking
import com.bootcamp_android.domain.repostories.ILotsRepository
import com.bootcamp_android.domain.util.Result

class LotRepository(
    var parkingService: ParkingService,private var parkingDataBase: ParkingDataBase
) : ILotsRepository {

    override suspend fun getDetailLot(id: Int): Lot? = getLots().find {
        it.parkingLot == id
    }

    override suspend fun getLots(): List<Lot> {
        val mutableLotList = mutableListOf<Lot>()
        val lotList = Parking(lotsForReserve = mutableLotList)
        val result = parkingService.getLots()

        if(result is Result.Success) {
            result.data.forEach { lot ->
                saveToDataBase(lot)
            }
        }
        lotList.lotsForReserve = getLocalInfo()
        return lotList.lotsForReserve
    }

    private suspend fun saveToDataBase(lot: LotResponse) {
        val localLot = LotMapperLocal().transformFromRepositoryToRoom(lot)

        parkingDataBase.getLotsDao().addLot(localLot)
    }

    private fun getLocalInfo(): MutableList<Lot> {
        val dataBaseLots = parkingDataBase.getLotsDao().getLots()
        val lotList = mutableListOf<Lot>()
        dataBaseLots.forEach {
            lotList.add(LotMapperLocal().transformFromRoomToDomain(it))
        }
        return lotList
    }
}