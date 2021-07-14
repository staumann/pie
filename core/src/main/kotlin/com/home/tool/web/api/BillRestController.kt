package com.home.tool.web.api

import com.home.tool.core.BillProcessor
import com.home.tool.model.Bill
import com.home.tool.model.DisplayBill
import com.home.tool.model.Response
import com.home.tool.service.BillService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestBody

@RestController
@RequestMapping("/bill")
class BillRestController(private val billService: BillService, private val billProcessor: BillProcessor) {

    @GetMapping("/all")
    fun getAllBills(): ResponseEntity<Iterable<Bill>> {
        return ResponseEntity.ok().body(billService.getAll())
    }

    @GetMapping("/get")
    fun getBill(@RequestParam("id") id: String): ResponseEntity<DisplayBill> {
        billProcessor.getDisplayInformation(id)?.apply {
            return ResponseEntity.ok().body(this)
        }
        return ResponseEntity.notFound().build()
    }

    @DeleteMapping("/delete")
    fun deleteBill(@RequestParam("id")id: String): ResponseEntity<Response> {
        billService.deleteBill(id)
        return ResponseEntity.ok().body(Response(true,"bill with id $id was deleted."))
    }

    @PostMapping("/store")
    fun storeBill(@RequestBody bill: Bill): ResponseEntity<Response> {
        val storedBill: Bill? = billService.storeBill(bill)
        storedBill?.apply {
            return ResponseEntity.ok().body(Response(true, "bill with id ${this.id} was stored."))
        }
        return ResponseEntity.internalServerError().build()
    }
}
