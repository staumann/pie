package com.home.tool.service

import com.home.tool.model.Category
import com.home.tool.repository.CategoryRepository
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.format.DateTimeFormatter

@Service
class CategoryService(private val repo: CategoryRepository) {

    fun getAll(): Iterable<Category> {
        return repo.findAll()
    }

    fun storeCategory(category: Category): Category {
        category.id = if (category.id == "") getIdForString(category.name) else category.id
        return repo.save(category)
    }

    fun getCategory(id: String): Category? {
        return repo.findById(id).orElseGet { null }
    }

    fun deleteCategory(id: String) {
        repo.deleteById(id)
    }
}