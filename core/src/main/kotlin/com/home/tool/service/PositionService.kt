package com.home.tool.service

import com.home.tool.repository.PositionRepository
import com.home.tool.model.Position
import org.springframework.stereotype.Service

interface PositionService {
    fun getPositionById(id: String): Position?

    fun getPositionsByBillId(id: String): Iterable<Position>

    fun getPositionsByCategoryId(id: String): Iterable<Position>

    fun storePositions(position: Iterable<Position>?): Iterable<Position>

    fun storePosition(position: Position): Position

    fun deletePosition(id: String)

    fun deletePositions(id: String)
}

@Service
class PositionServiceImpl(private val positionRepository: PositionRepository) : PositionService {

    override fun getPositionById(id: String): Position? = positionRepository.findById(id).orElseGet(null)

    override fun getPositionsByBillId(id: String): Iterable<Position> = positionRepository.findByBillId(id)

    override fun getPositionsByCategoryId(id: String): Iterable<Position> = positionRepository.findByCategory(id)

    override fun storePositions(position: Iterable<Position>?): Iterable<Position> =
        positionRepository.saveAll(position)

    override fun storePosition(position: Position): Position {
        position.id = if (position.id == "") getIdForString(position.billId) else position.id

        return positionRepository.save(position)
    }

    override fun deletePosition(id: String) = positionRepository.deleteById(id)

    override fun deletePositions(id: String) = positionRepository.deleteByBillId(id)
}
