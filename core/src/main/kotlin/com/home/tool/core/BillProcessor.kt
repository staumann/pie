package com.home.tool.core

import com.fasterxml.jackson.databind.ObjectMapper
import com.home.tool.model.*
import com.home.tool.service.BillService
import com.home.tool.service.PositionService
import com.home.tool.service.ShopService
import com.home.tool.service.UserService
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.Calendar

fun Double.round(): Double =BigDecimal.valueOf(this).setScale(2, RoundingMode.HALF_EVEN).toDouble()

@Component
class BillProcessor(
    private val billService: BillService,
    private val positionService: PositionService,
    private val shopService: ShopService,
    private val userService: UserService,
    private val objectMapper: ObjectMapper
) {

    private val sdf: SimpleDateFormat = SimpleDateFormat("dd.MM.yyyy")

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
        val results = getResultForBill(positions)
        return DisplayBill(
            bill.id,
            user,
            getTotalForBill(positions),
            sdf.format(bill.date.time),
            shop,
            positions,
            results
        )
    }

    private fun getResultForBill(positions: Iterable<Position>): Iterable<ResultEntry> {
        val (pooled, nonePooled) = positions.groupBy { it.targetId }.mapValues { (_, v) -> v.sumByDouble { it.amount } }
            .map { (k, v) ->
                val u = userService.getById(k)
                (u to ResultEntry(name = u?.firstName ?: "", payed = v))
            }.partition { it.first?.pool ?: false }
        val poolCount = pooled.size
        nonePooled.forEach { (_, entry) ->
            pooled.forEach { it.second.total = (it.second.total ?: 0.0) + entry.payed / poolCount }
        }
        val resultEntries: List<ResultEntry> = pooled.map { it.second }.onEach { it.total = (it.total ?: 0.0) + it.payed }
        return (nonePooled.map { it.second } + resultEntries).onEach {
            it.total?.round()
            it.payed.round()
        }
    }

    fun getOverViewInformation(): Iterable<DisplayBill> {
        return billService.getAll().map {
            convertBill(it)
        }
    }

    fun saveBill(bill: Bill?): Bill? = billService.storeBill(bill)

    fun createResultForTimeFrame(startDate: Calendar, endDate: Calendar): Result {
        val result = billService.getBillsForTimeFrame(startDate, endDate)
            .map { bill ->
                bill to positionService.getPositionsByBillId(bill.id).groupBy { it.targetId }
                    .mapValues { (k, v) -> v.sumByDouble { p -> p.amount } }
            }
            .groupBy { it.first.payedBy }.map {}
        println(objectMapper.writeValueAsString(result))
        return Result(emptyMap())
    }

    private fun getTotalForBill(positions: Iterable<Position>): Double =
        positions.sumByDouble { it.amount }.round()

}
