package com.home.tool.database.repository

import com.home.tool.model.Bill
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BillRepository: CrudRepository<Bill, String>