package com.home.tool.web

import com.home.tool.model.Category
import com.home.tool.service.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/category")
class CategoryRestController(private val service: CategoryService) {

    @GetMapping("/get")
    fun getCategory(@RequestParam(name = "id") id: String): ResponseEntity<Category> {
        service.getCategory(id)?.apply {
            return ResponseEntity.ok().body(this)
        }
        return ResponseEntity.notFound().build()
    }
}