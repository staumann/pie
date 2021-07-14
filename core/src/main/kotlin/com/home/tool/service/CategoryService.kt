package com.home.tool.service

import com.home.tool.model.Category
import com.home.tool.repository.CategoryRepository
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.format.DateTimeFormatter

interface CategoryService {
    fun getAll(): Iterable<Category>

    fun storeCategory(category: Category): Category

    fun getCategory(id: String): Category?

    fun deleteCategory(id: String)
}

@Service
class CategoryServiceImpl(private val repo: CategoryRepository): CategoryService {

    override fun getAll(): Iterable<Category> {
        return repo.findAll()
    }

    override fun storeCategory(category: Category): Category {
        category.id = if (category.id == "") getIdForString(category.name) else category.id
        return repo.save(category)
    }

    override fun getCategory(id: String): Category? {
        return repo.findById(id).orElseGet { null }
    }

    override fun deleteCategory(id: String) {
        repo.deleteById(id)
    }
}
