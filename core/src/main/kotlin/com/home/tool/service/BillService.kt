package com.home.tool.service

import com.home.tool.repository.BillRepository
import com.home.tool.model.Bill
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

interface BillService {
    fun getAll(): Iterable<Bill>

    fun getBillById(id: String): Bill?

    fun deleteBill(id: String)

    fun storeBill(bill: Bill?): Bill?

    fun getBillsForTimeFrame(startDate: Calendar, endDate: Calendar): Iterable<Bill>
}


@Service
class BillServiceImpl(private val billRepository: BillRepository) : BillService {
    override fun getAll(): Iterable<Bill> = billRepository.findAll()

    override fun getBillById(id: String): Bill? = billRepository.findById(id).orElse(null)

    override fun deleteBill(id: String) = billRepository.deleteById(id)

    override fun storeBill(bill: Bill?): Bill? {
        bill?.apply {
            this.id = if (this.id == "") getIdForString(this.shopId) else this.id
            return billRepository.save(this)
        }
        return null
    }

    override fun getBillsForTimeFrame(startDate: Calendar, endDate: Calendar): Iterable<Bill> =
        billRepository.findAllByTimeFrame(sdf.format(startDate.time), sdf.format(endDate.time))
}
