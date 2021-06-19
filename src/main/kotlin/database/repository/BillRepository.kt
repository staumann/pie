package com.home.tool.database.repository

import com.home.tool.model.Bill
import org.springframework.data.repository.CrudRepository

interface BillRepository: CrudRepository<Bill, String>