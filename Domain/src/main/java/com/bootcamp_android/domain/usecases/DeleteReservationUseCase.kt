package com.bootcamp_android.domain.usecases

import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.IReservationRepository
import com.bootcamp_android.domain.util.DeleteResult
import com.bootcamp_android.domain.util.Result

class DeleteReservationUseCase {

    lateinit var deleteRepository: IReservationRepository

    suspend operator fun invoke(res: Reservation,auth: String): DeleteResult {
        val ok: DeleteResult = if(res.authorizationCode == auth) {
            if(deleteRepository.deleteReservation(res.id) is Result.Success) {
                DeleteResult.SUCCESS_RESULT
            } else {
                DeleteResult.ERROR
            }
        } else {
            DeleteResult.BAD_AUTHORIZATION_CODE
        }


        return ok
    }
}