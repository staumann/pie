package com.home.tool.core

import com.home.tool.service.PositionService
import org.springframework.stereotype.Service

@Service
class CategoryProcessor(private val positionService: PositionService) {

    fun getTotalForCategory(categoryID: String): Double {
        val positions = positionService.getPositionsByCategoryId(categoryID)

        return positions.sumByDouble { p -> p.amount }
    }
}