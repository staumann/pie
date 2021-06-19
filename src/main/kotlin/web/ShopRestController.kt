package com.home.tool.web

import com.home.tool.model.Response
import com.home.tool.model.Shop
import com.home.tool.service.ShopService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/shop")
class ShopRestController(private val shopService: ShopService) {

    @GetMapping("/get")
    fun getShop(@RequestParam("id") id: String): ResponseEntity<Shop> {
        shopService.getShopById(id)?.apply {
            return ResponseEntity.ok(this)
        }
        return ResponseEntity.notFound().build()
    }

    @GetMapping("/store")
    fun storeShop(@RequestParam("id") id: String, @RequestParam("name") name: String, @RequestParam("category") category: String): ResponseEntity<Response> {
        shopService.storeShop(Shop(id, name, category))
        return ResponseEntity.ok(Response(true, "Shop with name $name was stored."))
    }
}