package com.bootcamp_android.data.room.mapper

import com.bootcamp_android.data.room.entities.ReservationRoom
import com.bootcamp_android.data.services.response.ReservationResponse
import com.bootcamp_android.domain.model.Reservation


class ReservationMapperLocal : BaseMapperRepository<ReservationRoom,Reservation,ReservationResponse> {
    override fun transformFromRoomToDomain(type: ReservationRoom): Reservation = Reservation(
        type.id,
        type.authorizationCode,
        type.starDateTimeInMillis,
        type.endDateTimeInMillis,
        type.parkingLot
    )

    override fun transformFromDomainToRoom(type: Reservation): ReservationRoom = ReservationRoom(
        type.id,
        type.authorizationCode,
        type.starDate,
        type.endDate,
        type.parkingLot
    )

    override fun transformFromRepositoryToDomain(type: ReservationResponse): Reservation = Reservation (

        type.id,
        type.authorizationCode,
        type.startDate.toLong(),
        type.endDate.toLong(),
        type.parkingLot
    )

    override fun transformFromRepositoryToRoom(type: ReservationResponse):ReservationRoom = ReservationRoom (

        type.id,
        type.authorizationCode,
        type.startDate.toLong(),
        type.endDate.toLong(),
        type.parkingLot
    )

}