package com.bootcamp_android.domain.usecases

import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.IReservationRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import  com.bootcamp_android.domain.util.Result

class DeleteReservationUseCaseTest {


        @RelaxedMockK
        private lateinit var deleteRepository: IReservationRepository

        lateinit var deleteUseCase: DeleteReservationUseCase

        @Before
        fun setUp(){
            MockKAnnotations.init(this)
            deleteUseCase = DeleteReservationUseCase()
            deleteUseCase.deleteRepository = deleteRepository
        }

        @Test
        fun rightDelete() = runBlocking {

            var reservation = Reservation("","1",1,20,0)
            coEvery { deleteRepository.deleteReservation(reservation,"1") } returns  Result.Success(true)

          val result =  deleteUseCase(reservation,"1")

            coVerify (exactly = 1 ) { deleteRepository.deleteReservation(reservation,"1") }
            assert(result == Result.Success(true))
        }

        @Test
        fun wrongDelete() = runBlocking {

            var reservation = Reservation("","1",1,10,0)
            coEvery { deleteRepository.deleteReservation(reservation,"1") } returns  Result.Success(false)

           val result= deleteUseCase(reservation,"wrong authorization code")
            assert(result == Result.Success(false))

            coVerify (exactly = 0 ) { deleteRepository.deleteReservation(reservation,"1") }
        }
    }