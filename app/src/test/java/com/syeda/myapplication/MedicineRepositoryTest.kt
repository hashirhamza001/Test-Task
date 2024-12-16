package com.syeda.myapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.syeda.myapplication.model.Medicine
import com.syeda.myapplication.repo.MedicineApi
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class MedicineRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Mock
    private lateinit var mockDao: MedicineDao

    @Mock
    private lateinit var mockApi: MedicineApi

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @Test
    fun `test successful data fetch db`() = testScope.runTest {
        val medicines =
            listOf(
                Medicine(id = 1, name = "Paracetamol", dose = "500mg", strength = "Mild"),
                Medicine(id = 2, name = "Aspirin", dose = "100mg", strength = "Moderate")
            )

        val flowMedicines =  flowOf(medicines)
        whenever(mockDao.getAllMedicines()).thenReturn(flowMedicines)

        // Then: Verify the result is as expected
        assertEquals(flowMedicines, mockDao.getAllMedicines())
    }
    @Test
    fun `test successful data deleted`() = testScope.runTest {
        val medicines =
            listOf(
                Medicine(id = 1, name = "Paracetamol", dose = "500mg", strength = "Mild"),
                Medicine(id = 2, name = "Aspirin", dose = "100mg", strength = "Moderate")
            )
        val flowMedicines =  flowOf(medicines)
        whenever(mockDao.getAllMedicines()).thenReturn(flowMedicines)
        whenever(mockDao.deleteMedicine(medicines[0])).thenReturn(Unit)
        mockDao.deleteMedicine(medicines[0])
        verify(mockDao).deleteMedicine(medicines[0])
    }
    @Test
    fun `test successful data fetch api`() = testScope.runTest {
        val medicines =
            listOf(
                Medicine(id = 1, name = "Paracetamol", dose = "500mg", strength = "Mild"),
                Medicine(id = 2, name = "Aspirin", dose = "100mg", strength = "Moderate")
            )

        whenever(mockApi.getMedicines()).thenReturn(medicines)

        // Then: Verify the result is as expected
        assertEquals(medicines, mockApi.getMedicines())
    }
}