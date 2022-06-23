package com.bootcamp_android.domain.usecases

import com.bootcamp_android.data.repositories.ReservationRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
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
        getReservationsUseCase.reservationRepository = reservationRepository
    }

    @Test
    fun `call to the repository one time`() = runBlocking {
        coEvery {
            reservationRepository.getReservations()
        } returns mockk()
        getReservationsUseCase.getReservation()
        coVerify(exactly = 1) {
            reservationRepository.getReservations()
        }
    }
}