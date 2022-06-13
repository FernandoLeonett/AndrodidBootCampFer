package com.bootcamp_android.data.services

import com.bootcamp_android.data.services.api.ApiService
import com.bootcamp_android.data.services.response.ParkingLotResponse
import com.bootcamp_android.data.services.response.ReservationResponse
import com.bootcamp_android.data.services.retrofit_instance.RetrofitInstance
import com.bootcamp_android.domain.util.Result
import com.bootcamp_android.domain.util.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ParkingService { companion object {

    const val PARKING_ID = Utils.parkingId
}

    suspend fun getLots(): Result<List<ParkingLotResponse>> {
        val resultList: Result<List<ParkingLotResponse>>
        withContext(Dispatchers.IO) {
            resultList = try {
                val response = RetrofitInstance.getRetrofit().create(ApiService::class.java).getParkingLots(
                    PARKING_ID
                )

                if(response.lotList.isNotEmpty()) {
                    Result.Success(response.lotList)
                } else {
                    Result.Failure(Exception("error"))
                }
            } catch(e: Exception) {
                Result.Failure(e)
            }
        }
        return resultList
    }

    suspend fun getReservations(): Result<List<ReservationResponse>> {
        var result: Result<List<ReservationResponse>>
        withContext(Dispatchers.IO) {
            result = try {
                val response = RetrofitInstance.getRetrofit().create(ApiService::class.java).getReservations(
                    PARKING_ID
                )


                if(response?.reservationList?.isNotEmpty()!!) {
                    Result.Success(response.reservationList)
                } else {
                    Result.Failure(Exception("error"))
                }
            } catch(e: Exception) {
                Result.Failure(e)
            }
        }
        return result
    }
}




