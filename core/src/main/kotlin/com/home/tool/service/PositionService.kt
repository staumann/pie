package com.home.tool.service

import com.home.tool.repository.PositionRepository
import com.home.tool.model.Position
import org.springframework.stereotype.Service

@Service
class PositionService(private val positionRepository: PositionRepository) {

    fun getPositionById(id: String): Position? = positionRepository.findById(id).orElseGet(null)

    fun getPositionsByBillId(id: String): Iterable<Position> = positionRepository.findByBillID(id)

    fun storePositions(position: Iterable<Position>?): Iterable<Position> = positionRepository.saveAll(position)

    fun storePosition(position: Position): Position {
        position.id = if (position.id == "") getIdForString(position.billID) else position.id

        return positionRepository.save(position)
    }

    fun deletePosition(id: String) = positionRepository.deleteById(id)

    fun deletePositions(id: String) = positionRepository.deleteByBillID(id)

}