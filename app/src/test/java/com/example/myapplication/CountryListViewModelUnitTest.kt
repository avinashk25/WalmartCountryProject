package com.example.myapplication

import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapplication.data.CountryAPIService
import com.example.myapplication.model.CountryItem
import com.example.myapplication.viewmodel.CountryListViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import java.lang.Exception

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CountryListViewModelUnitTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var service : CountryAPIService

    private lateinit var viewModel: CountryListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true, relaxUnitFun = true)
        viewModel = CountryListViewModel(service)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testViewModel() = runBlocking {
        // empty data test
        coEvery { service.getCountries() } returns emptyList()

        viewModel.onRefresh()
        delay(100)
        var value = viewModel.items.value
        assertEquals(0, value?.size)

        // dummy data
        val mockList = listOf(mockk<CountryItem>())
        coEvery { service.getCountries()} returns mockList

        viewModel.onRefresh()
        delay(100)
        value = viewModel.items.value

        assertEquals(mockList, value)

        // Throw an error
        coEvery { service.getCountries()} throws Exception("Test Exception")

        viewModel.onRefresh()
        delay(100)
        value = viewModel.items.value
        assertEquals(0, value?.size)
    }
}