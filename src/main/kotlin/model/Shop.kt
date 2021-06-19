package com.home.tool.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "shops")
data class Shop(
    @Id @Column(name = "shops") val id: String,
    @Column(name = "name") val name: String,
    @Column(name = "category") val category: String
)