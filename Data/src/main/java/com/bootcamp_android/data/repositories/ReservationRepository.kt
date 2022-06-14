package com.bootcamp_android.data.repositories

import com.bootcamp_android.data.services.ParkingService
import com.bootcamp_android.domain.model.Parking
import com.bootcamp_android.domain.model.Reservation

import com.bootcamp_android.domain.repostories.IReservationRepository
import com.bootcamp_android.domain.util.Result

class ReservationRepository : IReservationRepository {

    override suspend fun addReservation(res: Reservation): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteReservation(id: Int): Boolean {
        TODO("Not yet implemented")
    }

     var reservationService: ParkingService = ParkingService()
    override suspend fun getReservations(): List<Reservation> {
        var reservationList = mutableListOf<Reservation>()
        var reservations = Parking( reservations = reservationList)
        var result = reservationService.getReservations()



        if(result is Result.Success) {
            result.data.forEach { reservation ->
                reservationList.add(
                    Reservation(
                        reservation.id,
                        reservation.authorizationCode,
                        reservation.startDate.toLong(),
                        reservation.endDate.toLong(),
                        reservation.parkingLot
                    )
                )
            }
        }
        reservations.reservations = reservationList

        return reservations.reservations
    }
}
