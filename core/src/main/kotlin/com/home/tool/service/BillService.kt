package com.home.tool.service

import com.home.tool.repository.BillRepository
import com.home.tool.model.Bill
import org.springframework.stereotype.Service
import java.util.*

@Service
class BillService(private val billRepository: BillRepository) {
    fun getAll(): Iterable<Bill> = billRepository.findAll()

    fun getBillById(id: String): Bill? = billRepository.findById(id).orElse(null)

    fun deleteBill(id: String) = billRepository.deleteById(id)

    fun storeBill(bill: Bill?): Bill? {
        bill?.apply {
            if (id.isBlank()) {
                id = "${shopId}-${payedBy}-${date.get(Calendar.YEAR)}-${date.get(Calendar.MONTH)}-${date.get(Calendar.DAY_OF_MONTH)}"
                println("id is null setting id to $id")
            }
           return billRepository.save(this)
        }
        return null
    }
}