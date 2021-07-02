package com.home.tool.repository

import com.home.tool.model.Category
import org.springframework.data.repository.CrudRepository

interface CategoryRepository: CrudRepository<Category, String>