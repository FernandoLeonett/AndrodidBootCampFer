package com.bootcamp_android.data.services

import com.bootcamp_android.data.services.retrofit_instance.RetrofitInstance
import com.bootcamp_android.data.services.api.ApiService

import com.bootcamp_android.data.services.response.LotResponse
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.util.Result
import com.bootcamp_android.domain.util.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ParkingService { companion object {

    const val PARKING_ID = Utils.parkingId
}

    suspend fun getLots(): Result<List<LotResponse>> {
        val resultList: Result<List<LotResponse>>
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

    suspend fun getReservations(): Result<List<Reservation>> {
        var result: Result<List<Reservation>>
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
    suspend fun addReservation(parkingId: String, reservation: com.bootcamp_android.data.services.request.ReservationAddModel):Result<Boolean>{
        var result: Result<Boolean>
        withContext(Dispatchers.IO) {

            result = try {
                val response = RetrofitInstance.getRetrofit().create(ApiService::class.java).addReservations(
                    parkingId, reservation)
                if(response.isSuccessful){
                    Result.Success(true)
                }else{
                    Result.Failure(Exception(response.message()))
                }


            }catch (e: Exception){
                Result.Failure(e)
            }
        }
        return result
    }



    suspend fun deleteReservation(parkingId: String, reservationId: String):Result<Boolean>{
        var result: Result<Boolean>
        withContext(Dispatchers.IO) {

            result = try {
                val response = RetrofitInstance.getRetrofit().create(ApiService::class.java).deleteReservation(
                    parkingId, reservationId)
                if(response.isSuccessful){
                    Result.Success(true)
                }else{
                    Result.Failure(Exception(response.message()))
                }


            }catch (e: Exception){
                Result.Failure(e)
            }
        }
        return result
    }


}




