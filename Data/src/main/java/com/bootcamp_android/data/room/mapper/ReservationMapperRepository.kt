package com.bootcamp_android.data.room.mapper

import com.bootcamp_android.data.room.entities.ReservationRoom
import com.bootcamp_android.domain.model.Reservation

class ReservationMapperLocal : BaseMapperRepository<ReservationRoom,Reservation,Reservation> {

    override fun transformFromRoomToDomain(type: ReservationRoom): Reservation = Reservation(
        type.id,type.authorizationCode,type.starDateTimeInMillis,type.endDateTimeInMillis,type.parkingLot
    )

    override fun transformFromDomainToRoom(type: Reservation): ReservationRoom = ReservationRoom(
        type.id,type.authorizationCode,type.startDate,type.endDate,type.parkingLot
    )

    override fun transformFromRepositoryToDomain(type: Reservation): Reservation = Reservation(
        type.id,type.authorizationCode,type.startDate,type.endDate,type.parkingLot
    )

    override fun transformFromRepositoryToRoom(type: Reservation): ReservationRoom = ReservationRoom(
        type.id,type.authorizationCode,type.startDate,type.endDate,type.parkingLot
    )
}