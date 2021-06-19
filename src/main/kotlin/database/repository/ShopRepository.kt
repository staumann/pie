package com.home.tool.database.repository

import com.home.tool.model.Shop
import org.springframework.data.repository.CrudRepository

interface ShopRepository: CrudRepository<Shop, String>