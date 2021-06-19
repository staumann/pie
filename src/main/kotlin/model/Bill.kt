package com.home.tool.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "bills")
data class Bill(
    @Id @Column(name = "id") val id: String,
    @Column(name = "payedBy") val payedBy: String
)