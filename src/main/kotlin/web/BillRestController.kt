package com.home.tool.web

import com.home.tool.model.Bill
import com.home.tool.model.Response
import com.home.tool.service.BillService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/bill")
class BillRestController(private val billService: BillService) {

    @GetMapping("/all")
    fun getAllBills(): ResponseEntity<Iterable<Bill>> {
        return ResponseEntity.ok().body(billService.getAll())
    }

    @GetMapping("/get")
    fun getBill(@RequestParam("id") id: String): ResponseEntity<Bill> {
        billService.getBillById(id)?.apply {
            return ResponseEntity.ok().body(this)
        }
        return ResponseEntity.notFound().build()
    }

    @DeleteMapping("/delete")
    fun deleteBill(@RequestParam("id")id: String): ResponseEntity<Response> {
        billService.deleteBill(id)
        return ResponseEntity.ok().body(Response(true,"bill with id $id was deleted."))
    }
}