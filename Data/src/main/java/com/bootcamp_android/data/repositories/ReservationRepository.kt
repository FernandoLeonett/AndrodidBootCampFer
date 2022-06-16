package com.bootcamp_android.data.repositories

import com.bootcamp_android.data.services.ParkingService
import com.bootcamp_android.data.services.request.ReservationResponse
import com.bootcamp_android.domain.model.Parking
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.IReservationRepository
import com.bootcamp_android.domain.util.Result
import com.bootcamp_android.domain.util.Utils.parkingId

class ReservationRepository : IReservationRepository {

    var reservationService: ParkingService = ParkingService()
    override suspend fun addReservation(
     reservation: Reservation
    ): Result<Boolean> {
        val result = reservationService.addReservation(
            (parkingId),ReservationResponse(
                reservation.authorizationCode,
                reservation.starDateTimeInMillis,
                reservation.endDateTimeInMillis,
                reservation.parkingLot
            )
        )

        return when(result) {
            is Result.Success -> {
                Result.Success(result.data)
            }
            is Result.Failure -> {
                Result.Failure(result.exception)
            }
        }
    }



    override suspend fun deleteReservation(
       reservation:Reservation ,authorizationCode: String
    ): Result<Boolean> {
        var result = reservationService.deleteReservation(parkingId,reservation.id)

        return when(result) {
            is Result.Success -> {
                Result.Success(result.data)
            }
            is Result.Failure -> {
                Result.Failure(result.exception)
            }
        }
    }

    override suspend fun getReservations(): List<Reservation> {
        var reservationList = mutableListOf<Reservation>()
        var reservations = Parking(reservations = reservationList)
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
