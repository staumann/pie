package com.home.tool.core

import com.home.tool.model.Category
import com.home.tool.service.CategoryService
import com.home.tool.service.PositionService
import org.springframework.stereotype.Service

@Service
class CategoryProcessor(private val positionService: PositionService, private val categoryService: CategoryService) {

    fun getTotalForCategory(categoryID: String): Double {
        val positions = positionService.getPositionsByCategoryId(categoryID)

        return positions.sumByDouble { p -> p.amount }
    }

    fun getAllCategories(): Iterable<Category> {
        return categoryService.getAll()
    }
}
