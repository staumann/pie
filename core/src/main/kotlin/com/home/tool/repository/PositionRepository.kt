package com.home.tool.repository

import com.home.tool.model.Position
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PositionRepository: CrudRepository<Position, String> {
    fun findByBillID(id: String): Iterable<Position>

    fun deleteByBillID(id: String)
}