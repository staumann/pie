package com.home.tool.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "shops")
data class Shop(
    @Id @Column(name = "id") val id: String = "",
    @Column(name = "name") val name: String = "",
    @Column(name = "category_id") val category: String = ""
)