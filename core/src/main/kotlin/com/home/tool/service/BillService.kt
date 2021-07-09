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
            this.id = if (this.id == "") getIdForString(this.shopId) else this.id
            return billRepository.save(this)
        }
        return null
    }
}