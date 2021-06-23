package com.home.tool.service

import com.home.tool.database.repository.PositionRepository
import com.home.tool.model.Position

class PositionService(private val positionRepository: PositionRepository) {

    fun getPositionById(id: String): Position? = positionRepository.findById(id).orElseGet(null)

    fun getPositionsByBillId(id: String): Iterable<Position> = positionRepository.findByBillId(id)

    fun storePositions(position: Iterable<Position>?): Iterable<Position> = positionRepository.saveAll(position)

    fun deletePosition(id: String) = positionRepository.deleteById(id)

    fun deletePositions(id: String) = positionRepository.deleteByBillId(id)

}