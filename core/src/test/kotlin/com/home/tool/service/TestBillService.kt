package com.home.tool.service

import com.home.tool.model.Bill
import com.home.tool.repository.BillRepository
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class TestBillService {


    @Test
    fun testStore() {
        val repoMock: BillRepository = Mockito.mock(BillRepository::class.java)
        val service = BillService(repoMock)
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, 2021)
        cal.set(Calendar.MONTH, 5)
        cal.set(Calendar.DAY_OF_MONTH, 10)
        val testBill = Bill("", "me", "1", cal)
        Mockito.`when`(repoMock.save(testBill)).thenReturn(testBill)
        val resultBill = service.storeBill(testBill)

        Mockito.verify(repoMock, Mockito.times(1)).save(Mockito.any())
        Assert.assertEquals("1-me-2021-5-10",resultBill?.id)
    }
}