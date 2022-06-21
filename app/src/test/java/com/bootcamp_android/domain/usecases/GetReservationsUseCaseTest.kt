package com.bootcamp_android.domain.usecases

import com.bootcamp_android.data.repositories.ReservationRepository
import com.bootcamp_android.domain.repostories.IReservationRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetReservationsUseCaseTest {

    @RelaxedMockK
    private lateinit var reservationRepository: ReservationRepository
    private lateinit var getReservationsUseCase: GetReservationsUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getReservationsUseCase = GetReservationsUseCase()
        getReservationsUseCase.reservationRepository    = reservationRepository

    }

    @Test
    fun `get reservations`() = runBlocking {
        coEvery {
            reservationRepository.getReservations()
        } returns emptyList()
        //given
        val result =GetReservationsUseCase()
        assert(result.reservationRepository.getReservations().isEmpty())
        //then
//        coVerify {
//            reservationRepository.getReservations()
//        }
    }
}