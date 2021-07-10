package com.home.tool.web.api

import com.home.tool.model.Response
import com.home.tool.model.Shop
import com.home.tool.service.ShopService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/shop")
class ShopRestController(private val shopService: ShopService) {

    @GetMapping("/all")
    fun getAll(): ResponseEntity<Iterable<Shop>> {
        return ResponseEntity.ok().body(shopService.getAllShops())
    }

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

    @PostMapping("/store")
    fun storeShop(@RequestBody shop: Shop): ResponseEntity<Response> {
        shopService.storeShop(shop)
        return ResponseEntity.ok().body(Response(true, "Store shop with name ${shop.name}."))
    }

    @DeleteMapping("/delete")
    fun deleteShop(@RequestParam("id")id: String): ResponseEntity<Response> {
        shopService.deleteShop(id)
        return ResponseEntity.ok().body(Response(true, "Deleted Shop with id $id."))
    }
}