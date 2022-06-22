package com.bootcamp_android.domain.usecases

import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.IReservationRepository
import com.bootcamp_android.domain.util.AddResult
import com.bootcamp_android.domain.util.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ValidateReservationUseCaseTest{


     @RelaxedMockK
     private lateinit var reservationRepository: IReservationRepository
     private lateinit var validateReservationUseCase: ValidateReservationUseCase

     @Before
     fun onBefore() {
         MockKAnnotations.init(this)

         validateReservationUseCase = ValidateReservationUseCase()
         validateReservationUseCase.apply {
             this.validateReservationRepository = reservationRepository
         }

     }

     @Test
     fun `verify i can  add a reservation if the lot is buys`() = runBlocking {
         val reservation1 = Reservation("1","1",System.currentTimeMillis(),System.currentTimeMillis() + 1,1)
         val reservation2 = Reservation("1","1",System.currentTimeMillis()+2,System.currentTimeMillis() + 3,1)

         coEvery { reservationRepository.getReservations() } returns listOf()

         var result = validateReservationUseCase(reservation1)

         assert(result == AddResult.IS_FREE)
         //coVerify(exactly = 1) { reservationRepository.addReservation(reservation, true) is Result.Success}
     }





}