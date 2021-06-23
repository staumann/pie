package com.home.tool.service

import com.home.tool.database.repository.BillRepository
import com.home.tool.model.Bill
import org.springframework.stereotype.Service

@Service
class BillService(private val billRepository: BillRepository) {

    fun getAll(): Iterable<Bill> = billRepository.findAll()

    fun getBillById(id: String): Bill? = billRepository.findById(id).orElse(null)

    fun deleteBill(id: String) = billRepository.deleteById(id)

    fun storeBill(bill: Bill?) {
        bill?.apply {
            billRepository.save(this)
        }
    }
}