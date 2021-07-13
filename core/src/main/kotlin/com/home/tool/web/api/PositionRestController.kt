package com.home.tool.web.api

import com.home.tool.model.Position
import com.home.tool.model.Response
import com.home.tool.service.PositionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/position")
class PositionRestController(private val positionService: PositionService) {

    @GetMapping("/get")
    fun getPosition(@RequestParam("id") id: String): ResponseEntity<Position> {
        positionService.getPositionById(id)?.apply {
            return ResponseEntity.ok().body(this)
        }
        return ResponseEntity.notFound().build()
    }

    @GetMapping("/get-bill")
    fun getPositions(@RequestParam("id") id: String): ResponseEntity<Iterable<Position>> {
        val positions: Iterable<Position> = positionService.getPositionsByBillId(id)
        if (positions.toList().isNotEmpty()) {
            return ResponseEntity.ok().body(positions)
        }
        return ResponseEntity.notFound().build()
    }

    @PostMapping("/store/all")
    fun storePosition(@RequestBody positions: Iterable<Position>): ResponseEntity<Response> {
        val count: Int = positionService.storePositions(positions).toList().size
        if (count > 0) {
            return ResponseEntity.ok().body(Response(true, "stored $count positions"))
        }
        return ResponseEntity.internalServerError().body(Response(false, "could not save positions"))
    }

    @PostMapping("/store")
    fun storePosition(@RequestBody position: Position): ResponseEntity<Response> {
        positionService.storePosition(position)
        return ResponseEntity.ok().body(Response(true, "stored new position for ${position.billId} positions"))
    }

    @DeleteMapping("/delete")
    fun deletePositionById(@RequestParam("id")id: String): ResponseEntity<Response> {
        positionService.deletePosition(id)
        return ResponseEntity.ok().body(Response(true, "deleted position with id $id"))
    }

    @DeleteMapping("/delete-bill")
    fun deletePositionsByBill(@RequestParam("id") id: String): ResponseEntity<Response> {
        positionService.deletePositions(id)
        return ResponseEntity.ok().body(Response(true, "deleted bill positions with id: $id"))
    }
}