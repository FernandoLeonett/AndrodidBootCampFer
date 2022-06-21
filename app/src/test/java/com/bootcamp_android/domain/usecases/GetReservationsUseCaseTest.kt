package com.bootcamp_android.domain.usecases

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
    private lateinit var reservationRepository: IReservationRepository
    private lateinit var getReservationsUseCase: GetReservationsUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        val case = GetReservationsUseCase()
        case.reservationRepository = reservationRepository
        getReservationsUseCase = case
    }

    @Test
    fun `when the lot is not selected return error parking lot`() = runBlocking {
        coEvery {
            reservationRepository.getReservations()
        } returns emptyList()
        //given
        GetReservationsUseCase()
        //then
        coVerify {
            reservationRepository.getReservations()
        }
    }
}