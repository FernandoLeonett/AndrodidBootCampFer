package com.bootcamp_android.data.repositories

import com.bootcamp_android.data.room.localdatabase.ParkingDataBase
import com.bootcamp_android.data.room.mapper.ReservationMapperLocal
import com.bootcamp_android.data.services.ParkingService
import com.bootcamp_android.data.services.request.ReservationRequest
import com.bootcamp_android.domain.model.Parking
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.IReservationRepository
import com.bootcamp_android.domain.util.AddResult
import com.bootcamp_android.domain.util.Result

class ReservationRepository(
    private var parkingService: ParkingService,private var parkingDataBase: ParkingDataBase
) : IReservationRepository {

    override suspend fun getReservations(): List<Reservation> {
        val mutableReservationList = mutableListOf<Reservation>()
        val reservationList = Parking(reservations = mutableReservationList)
        val result = parkingService.getReservations()

        if(result is Result.Success) {
            result.data.forEach { reservation ->
                saveToDataBase(reservation)
            }
        }
        reservationList.reservations = getLocalInfo()

        return reservationList.reservations
    }

    private suspend fun saveToDataBase(reservation: Reservation) {
        val localReservation = ReservationMapperLocal().transformFromRepositoryToRoom(reservation)

        parkingDataBase.getReservationDao().addReservation(localReservation)
    }

    private fun getLocalInfo(): MutableList<Reservation> {
        val databaseReservations = parkingDataBase.getReservationDao().getReservations()
        val reservationList = mutableListOf<Reservation>()
        databaseReservations.forEach {
            reservationList.add(ReservationMapperLocal().transformFromRoomToDomain(it))
        }
        return reservationList
    }

    override suspend fun deleteReservation(resID: String): Result<Boolean> {
      return  when(deleteFromRemote(resID)) {
            is Result.Success ->{  deleteFromLocal(resID)}
            else -> Result.Failure(Exception())
        }
    }

    override suspend fun addReservation(
        res: Reservation
    ): Result<Boolean> {
        return when(resToRemote(res)) {
            is Result.Success -> Result.Success(true)
            else -> Result.Failure(Exception("ERROR"))
        }
    }

    private suspend fun deleteFromRemote(resId: String): Result<Boolean> {
        return when(val result = parkingService.deleteReservation(resId)) {
            is Result.Success -> {
                Result.Success(result.data)
            }
            is Result.Failure -> {
                Result.Failure(result.exception)
            }
            else -> return Result()
        }
    }

    private suspend fun deleteFromLocal(resId: String):Result<Boolean> {
         parkingDataBase.getReservationDao().deleteReservation(resId)
        return  Result.Success(true)
    }

    private suspend fun resToRemote(res: Reservation): Result<Boolean> {
        val newReservation = ReservationRequest(
            res.authorizationCode,res.startDate,res.endDate,res.parkingLot
        )

        return when(val result = parkingService.addReservation(newReservation)) {
            is Result.Success -> {
                Result.Success(true)
            }
            is Result.Failure -> {
                Result.Failure(result.exception)
            }
            else -> return Result()
        }
    }
}
