package com.bootcamp_android.domain.usecases

import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.IReservationRepository
import com.bootcamp_android.domain.util.DeleteReservationRequest
import com.bootcamp_android.domain.util.Result

class DeleteReservationUseCase {

    lateinit var deleteRepository: IReservationRepository

    suspend operator fun invoke(res: Reservation,auth: String): DeleteReservationRequest {
        val ok: DeleteReservationRequest = if(res.authorizationCode == auth) {
            if(deleteRepository.deleteReservation(res.id) is Result.Success) {
                DeleteReservationRequest.SUCCESS_RESULT
            } else {
                DeleteReservationRequest.ERROR
            }
        } else {
            DeleteReservationRequest.BAD_AUTHORIZATION_CODE
        }


        return ok
    }
}