package com.home.tool.core

import com.home.tool.model.Bill
import com.home.tool.model.DisplayBill
import com.home.tool.model.Position
import com.home.tool.model.Shop
import com.home.tool.service.BillService
import com.home.tool.service.PositionService
import com.home.tool.service.ShopService
import com.home.tool.service.UserService
import org.springframework.stereotype.Component
import java.time.format.DateTimeFormatter
import java.text.SimpleDateFormat




@Component
class BillProcessor(
    private val billService: BillService,
    private val positionService: PositionService,
    private val shopService: ShopService,
    private val userService: UserService
) {

    val sdf:SimpleDateFormat = SimpleDateFormat("dd.MM.yyyy")

    fun getDisplayInformation(billID: String): DisplayBill? {
        val bill: Bill? = billService.getBillById(billID)
        bill?.apply {
            return convertBill(this)
        }
        return null
    }

    private fun convertBill(bill: Bill): DisplayBill {
        val user = userService.getById(bill.payedBy)
        val positions: Iterable<Position> = positionService.getPositionsByBillId(bill.id)
        val shop: Shop? = shopService.getShopById(bill.shopId)
        return DisplayBill(bill.id, user, getTotalForBill(positions), sdf.format(bill.date.time), shop, positions)
    }

    fun getOverViewInformation(): Iterable<DisplayBill> {
        return billService.getAll().map {
            convertBill(it)
        }
    }

    private fun getTotalForBill(positions: Iterable<Position>): Double =  positions.sumByDouble { it.amount }
}