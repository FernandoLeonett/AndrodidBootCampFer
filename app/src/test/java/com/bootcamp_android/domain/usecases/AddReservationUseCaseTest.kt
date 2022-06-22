package com.bootcamp_android.domain.usecases

import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.IReservationRepository
import com.bootcamp_android.domain.util.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class AddReservationUseCaseTest {

    @RelaxedMockK
    private lateinit var reservationRepository: IReservationRepository
    private lateinit var addReservationUseCase: AddReservationUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)

        addReservationUseCase = AddReservationUseCase()
        addReservationUseCase.apply {
            this.addReservationRepository = reservationRepository
        }

    }

    @Test
    fun `verify connect one time with reservation repository`() = runBlocking {
        val reservation = Reservation("1","1",System.currentTimeMillis(),System.currentTimeMillis() + 1,12)

        coEvery { reservationRepository.addReservation(reservation) } returns Result.Success(true)

        addReservationUseCase(reservation)


        coVerify(exactly = 1) { reservationRepository.addReservation(reservation) is Result.Success}
    }

    @Test
    fun `verify no connect one time with reservation repository`() = runBlocking {
        val reservation = Reservation("1","1",System.currentTimeMillis(),System.currentTimeMillis() + 1,12)

        coEvery { reservationRepository.addReservation(reservation) } returns Result.Failure(Exception())

        var result = addReservationUseCase(reservation)


        coVerify(exactly = 0) { reservationRepository.addReservation(reservation)}
    }

    @Test
    fun `verify no connect one time with reservation repository assert`() = runBlocking {
        val reservation = Reservation("1","1",System.currentTimeMillis(),System.currentTimeMillis() + 1,12)
        val exception = Exception()
        coEvery { reservationRepository.addReservation(reservation) } returns Result.Failure(exception)

        var result = addReservationUseCase(reservation)


        coVerify() { reservationRepository.addReservation(reservation)}

        assert(result == Result.Failure(exception))

    }

}