package com.home.tool.repository

import com.home.tool.model.Bill
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface BillRepository: CrudRepository<Bill, String> {

    @Query("SELECT * FROM `bills` WHERE billing_date > :startDate AND billing_date < :endDate", nativeQuery = true)
    fun findAllByTimeFrame(@Param("startDate") startDate: String, @Param("endDate") endDate: String): Iterable<Bill>
}
