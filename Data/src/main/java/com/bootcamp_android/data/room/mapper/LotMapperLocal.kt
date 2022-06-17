package com.bootcamp_android.data.room.mapper

import com.bootcamp_android.data.room.entities.LotRoom
import com.bootcamp_android.data.room.mapper.BaseMapperRepository
import com.bootcamp_android.data.services.response.LotResponse
import com.bootcamp_android.domain.model.Lot

class LotMapperLocal : BaseMapperRepository<LotRoom,Lot,LotResponse> {

    override fun transformFromRoomToDomain(type: LotRoom): Lot = Lot(
        type.parkingLot
    )


    override fun transformFromDomainToRoom(type: Lot): LotRoom = LotRoom(
        type.parkingLot
    )

    override fun transformFromRepositoryToDomain(type: LotResponse): Lot = Lot(
        type.parkingLot
    )

    override fun transformFromRepositoryToRoom(type: LotResponse): LotRoom = LotRoom(
        type.parkingLot
    )

}