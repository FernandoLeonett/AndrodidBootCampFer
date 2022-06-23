package com.bootcamp_android.domain.usecases

import com.bootcamp_android.data.repositories.LotRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetLotsUseCaseTest {

    @RelaxedMockK
    private lateinit var lotRepository: LotRepository
    private lateinit var getLotsUseCase: GetLotsUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getLotsUseCase = GetLotsUseCase()
        getLotsUseCase.lotRepository = lotRepository
    }

    @Test
    fun `call to the repository one time`() = runBlocking {
        coEvery {
            lotRepository.getLots()
        } returns emptyList()
        getLotsUseCase.getLots()

        coVerify(exactly = 1) {
            lotRepository.getLots()
        }
    }
}



