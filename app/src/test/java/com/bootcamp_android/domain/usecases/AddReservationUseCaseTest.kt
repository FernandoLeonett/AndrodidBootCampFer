package com.bootcamp_android.domain.usecases

import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.IReservationRepository
import com.bootcamp_android.domain.util.AddResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import com.bootcamp_android.domain.util.Result
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*

internal class AddReservationUseCaseTest {

    @RelaxedMockK
    private lateinit var reservationRepository: IReservationRepository
    private lateinit var addReservationUseCase: AddReservationUseCase

    private var startDate: Calendar = Calendar.getInstance()
    private var finalDate = Calendar.getInstance()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        startDate[Calendar.YEAR] = 2022
        startDate[Calendar.MONTH] = 5
        startDate[Calendar.DAY_OF_MONTH] = 25
        startDate[Calendar.HOUR_OF_DAY] = 1
        startDate[Calendar.MINUTE] = 0
        finalDate[Calendar.YEAR] = 2022
        finalDate[Calendar.MONTH] = 5
        finalDate[Calendar.DAY_OF_MONTH] = 25
        finalDate[Calendar.HOUR_OF_DAY] = 2
        finalDate[Calendar.MINUTE] = 0
        addReservationUseCase = AddReservationUseCase()
        addReservationUseCase.apply {
            this.addReservationRepository = reservationRepository
        }
    }

    @Test
    fun `adding a reservation without auth code`() =runBlocking {
        val reservation = Reservation("1","",finalDate.timeInMillis,startDate.timeInMillis,11)
        coEvery { reservationRepository.addReservation(reservation) }
        val resultUseCase = addReservationUseCase(reservation)
        coVerify(exactly = 0) { reservationRepository.addReservation(reservation) }

        assert(resultUseCase == AddResult.BAD_REQUEST)
    }


    @Test
    fun `adding a wrong order date`() =runBlocking {
        val reservation = Reservation("1","1",finalDate.timeInMillis,startDate.timeInMillis,11)
        coEvery { reservationRepository.addReservation(reservation) }
        val resultUseCase = addReservationUseCase(reservation)
        coVerify(exactly = 0) { reservationRepository.addReservation(reservation) }

        assert(resultUseCase == AddResult.ORDER_DATE)
    }
    @Test
    fun `adding reservation successfully only one call to repository`() = runBlocking {
        val reservation = Reservation("1","1",startDate.timeInMillis,finalDate.timeInMillis,11)
        coEvery { reservationRepository.addReservation(reservation) } returns Result.Success(true)
        val result = addReservationUseCase(reservation)
        coVerify(exactly = 1) { reservationRepository.addReservation(reservation) }

        assert(result == AddResult.SUCCESS)
    }
    @Test
    fun `adding a reservation in a busy lot is not calling the repository`() = runBlocking {
        val reservation = Reservation("1","1",startDate.timeInMillis,finalDate.timeInMillis,1)

        coEvery { reservationRepository.getReservations() } returns listOf(
            Reservation(
                "1","1",startDate.timeInMillis,finalDate.timeInMillis,1
            )
        )
        val result = addReservationUseCase(reservation)
        coVerify(exactly = 0) { reservationRepository.addReservation(reservation) }

        assert(result == AddResult.IS_BUSY)
    }
}