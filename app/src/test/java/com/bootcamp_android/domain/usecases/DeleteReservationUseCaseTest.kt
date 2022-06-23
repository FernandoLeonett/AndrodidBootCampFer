package com.bootcamp_android.domain.usecases

import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.repostories.IReservationRepository
import com.bootcamp_android.domain.util.DeleteResult
import com.bootcamp_android.domain.util.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*

class DeleteReservationUseCaseTest {

    @RelaxedMockK
    private lateinit var reservationsRepository: IReservationRepository
    lateinit var deleteUseCase: DeleteReservationUseCase
    private var startDate: Calendar = Calendar.getInstance()
    private var finalDate = Calendar.getInstance()

    @Before
    fun setUp() {
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
        deleteUseCase = DeleteReservationUseCase()
        deleteUseCase.deleteRepository = reservationsRepository
    }

    @Test
    fun `deleting successfully`() = runBlocking {
        val reservation = Reservation("1","1",startDate.timeInMillis,finalDate.timeInMillis,11)
        coEvery { reservationsRepository.deleteReservation("1") } returns Result.Success(true)
        val resultUseCase = deleteUseCase(reservation,"1")


        coVerify(exactly = 1) { reservationsRepository.deleteReservation("1") }

        assert(resultUseCase == DeleteResult.SUCCESS_RESULT)
    }
    @Test
    fun `bad authorization code`() = runBlocking {
        val reservation = Reservation("1","1",startDate.timeInMillis,finalDate.timeInMillis,11)
        coEvery { reservationsRepository.deleteReservation("1") } returns Result.Success(true)
        val resultUseCase = deleteUseCase(reservation,"a wrong code")


        coVerify(exactly = 0) { reservationsRepository.deleteReservation("1") }

        assert(resultUseCase == DeleteResult.BAD_AUTHORIZATION_CODE)
    }
}