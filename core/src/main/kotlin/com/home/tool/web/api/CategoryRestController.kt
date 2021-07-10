package com.home.tool.web.api

import com.home.tool.model.Category
import com.home.tool.model.Response
import com.home.tool.service.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/category")
class CategoryRestController(private val service: CategoryService) {

    @GetMapping("/all")
    fun getAll(): ResponseEntity<Iterable<Category>> {
        return ResponseEntity.ok().body(service.getAll())
    }

    @GetMapping("/get")
    fun getCategory(@RequestParam(name = "id") id: String): ResponseEntity<Category> {
        service.getCategory(id)?.apply {
            return ResponseEntity.ok().body(this)
        }
        return ResponseEntity.notFound().build()
    }

    @PostMapping("/store")
    fun storeCategory(@RequestBody category:Category): ResponseEntity<Response> {
        val storedCategory = service.storeCategory(category)
        return ResponseEntity.ok().body(Response(true, "Store category with id: ${storedCategory.id}"))
    }

    @DeleteMapping("/delete")
    fun deleteCategory(@RequestParam(name = "id" )id: String): ResponseEntity<Response> {
        service.deleteCategory(id)
        return ResponseEntity.ok().body(Response(true, "Deleted category with id: $id"))
    }
}