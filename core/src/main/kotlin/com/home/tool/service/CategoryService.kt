package com.home.tool.service

import com.home.tool.model.Category
import com.home.tool.repository.CategoryRepository
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.format.DateTimeFormatter

@Service
class CategoryService(private val repo: CategoryRepository) {

    fun storeCategory(category: Category): Category {
        if (category.id == "") {
            category.id = category.name + DateTimeFormatter.ISO_INSTANT.format(Instant.now())
        }
        return repo.save(category)
    }

    fun getCategory(id: String): Category? {
        return repo.findById(id).orElseGet { null }
    }
}