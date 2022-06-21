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
import com.bootcamp_android.domain.util.Result

internal class AddReservationUseCaseTest {

    @RelaxedMockK
    private lateinit var reservationRepository: IReservationRepository
    private lateinit var addReservationUseCase: AddReservationUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)

        addReservationUseCase = AddReservationUseCase()
        addReservationUseCase.addReservationRepository = reservationRepository
    }

    @Test
    fun `add correct reservation`() = runBlocking {
        val reservation = Reservation("","6",7,8,0)

        coEvery { reservationRepository.addReservation(reservation) } returns Result.Success(true)
        var result = addReservationUseCase(reservation)

        assert(result == Result.Success(true))
        coVerify(exactly = 1) { reservationRepository.addReservation(reservation) }
    }
}