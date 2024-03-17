package com.example.coffeeshop

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.coffeeshop.core.ApiResult
import com.example.coffeeshop.data.remote.repo.ApiServiceRepo
import com.example.coffeeshop.data.remote.response.Product
import com.example.coffeeshop.data.remote.response.StoreInfoResponse
import com.example.coffeeshop.ui.home.HomeViewModel
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

class HomeViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    private lateinit var repo: ApiServiceRepo

    private lateinit var viewModel: HomeViewModel

    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        repo = mock()
        viewModel = HomeViewModel(repo)
    }

    @Test
    fun getStoreInfoTest() = runTest {
        Mockito.`when`(repo.getStoreInfo()).thenReturn(storeInfoMockResult())
        viewModel.getStoreInfo()
        verify(repo, times(1)).getStoreInfo()
        dispatcher.scheduler.advanceUntilIdle()
        assert(viewModel.storeInfo.value is StoreInfoResponse)
    }

    @Test
    fun getProductsTest() = runTest {
        Mockito.`when`(repo.getProducts()).thenReturn(productsMockResult())
        viewModel.getProducts()
        verify(repo, times(1)).getProducts()
        dispatcher.scheduler.advanceUntilIdle()
        assert(viewModel.productList.value is List<Product>)
    }

    private fun storeInfoMockResult() = ApiResult.Success(
        Response.success(
            StoreInfoResponse(
                "The Coffee Shop",
                4.5,
                "15:01:01.772Z",
                "19:45:51.365Z"
            )
        )
    )

    private fun productsMockResult() = ApiResult.Success(
        Response.success(
            listOf(
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
            )
        )
    )

}