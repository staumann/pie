package com.home.tool.database.repository

import com.home.tool.model.Shop
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ShopRepository: CrudRepository<Shop, String>