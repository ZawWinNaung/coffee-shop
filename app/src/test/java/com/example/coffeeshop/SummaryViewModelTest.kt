package com.example.coffeeshop

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.coffeeshop.core.ApiResult
import com.example.coffeeshop.data.remote.repo.ApiServiceRepo
import com.example.coffeeshop.data.remote.request.OrderRequestModel
import com.example.coffeeshop.data.remote.response.Product
import com.example.coffeeshop.ui.summary.SummaryViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import retrofit2.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mockito

class SummaryViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    private lateinit var repo: ApiServiceRepo

    private lateinit var viewModel: SummaryViewModel

    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        repo = mock()
        viewModel = SummaryViewModel(repo)
    }

    @Test
    fun makeOrderTest() = runTest {
        Mockito.`when`(repo.makeOrder(mockRequest)).thenReturn(
            ApiResult.Success(
                Response.success(
                    Any()
                )
            )
        )
        viewModel.makeOrder(mockRequest)
        dispatcher.scheduler.advanceUntilIdle()
        assert(viewModel.success.value is Boolean)
        verify(repo).makeOrder(mockRequest)
    }

    private val mockRequest = OrderRequestModel(
        products = listOf(
            Product(
                "Latte",
                50,
                "https://www.nespresso.com/ncp/res/uploads/recipes/nespresso-recipes-Latte-Art-Tulip.jpg"
            ),
            Product(
                "Dark Tiramisu Mocha",
                75,
                "https://www.nespresso.com/shared_res/mos/free_html/sg/b2b/b2ccoffeerecipes/listing-image/image/dark-tiramisu-mocha.jpg"
            )
        ),
        deliveryAddress = "CDC O4 Office, Bangkapi, Bangkok, 10310"
    )
}