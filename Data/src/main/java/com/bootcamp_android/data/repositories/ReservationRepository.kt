package com.bootcamp_android.data.repositories

import com.bootcamp_android.data.room.localdatabase.ParkingDataBase
import com.bootcamp_android.data.room.mapper.ReservationMapperLocal
import com.bootcamp_android.data.services.ParkingService
import com.bootcamp_android.domain.model.Parking
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.IReservationRepository
import com.bootcamp_android.domain.util.Result
import com.bootcamp_android.domain.util.Utils.parkingId
import com.bootcamp_android.data.services.request.ReservationRequest


class ReservationRepository(
    private var parkingService: ParkingService,private var parkingDataBase: ParkingDataBase
) : IReservationRepository {

    override suspend fun getReservations(): List<Reservation> {
        val mutableReservationList = mutableListOf<Reservation>()
        val reservationList = Parking( reservations = mutableReservationList)

        val result =  parkingService.getReservations()

        if (result is Result.Success) {
            result.data.forEach {reservation ->
                saveToDataBase(reservation)
            }

        }
        reservationList.reservations = getLocalInfo()

        return reservationList.reservations
    }

    private suspend fun saveToDataBase(reservation: Reservation){
        val localReservation = ReservationMapperLocal().transformFromRepositoryToRoom(reservation)

        parkingDataBase.getReservationDao().addReservation(localReservation)
        parkingDataBase.getReservationDao().deleteReservation(localReservation)
    }
    private fun getLocalInfo(): MutableList<Reservation>{
        val databaseReservations =  parkingDataBase.getReservationDao().getReservations()
        val reservationList = mutableListOf<Reservation>()
        databaseReservations.forEach{
            reservationList.add(ReservationMapperLocal().transformFromRoomToDomain(it))

        }
        return reservationList
    }

    override suspend fun addReservation(
        reservation: Reservation
    ): Result<Boolean> {
        val result = parkingService.addReservation(
            (parkingId),ReservationRequest(
                reservation.authorizationCode,reservation.starDate,reservation.endDate,reservation.parkingLot
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
        reservation: Reservation,authorizationCode: String
    ): Result<Boolean> {
        var result = parkingService.deleteReservation(parkingId,reservation.id)

        return when(result) {
            is Result.Success -> {
                Result.Success(result.data)
            }
            is Result.Failure -> {
                Result.Failure(result.exception)
            }
        }
        saveToDataBase(reservation)
    }
}
