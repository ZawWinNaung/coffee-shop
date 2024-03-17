package com.example.coffeeshop

import com.example.coffeeshop.core.ApiResult
import com.example.coffeeshop.data.remote.repo.ApiServiceRepo
import com.example.coffeeshop.data.remote.response.StoreInfoResponse
import com.example.coffeeshop.ui.home.HomeViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @Mock
    private lateinit var repo: ApiServiceRepo

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(repo)
    }

    @Test
    fun testGetStoreInfo() = runTest {
        `when`(repo.getStoreInfo()).thenReturn(
            storeInfoMockResult()
        )
        viewModel.getStoreInfo()
        verify(repo).getStoreInfo()
        assertEquals(storeInfoMockResult(), viewModel.storeInfo.value)
    }

    private fun storeInfoMockResult() = ApiResult.Success(
        Response.success(
            StoreInfoResponse(
                "", 0.0, "", ""
            )
        )
    )

}