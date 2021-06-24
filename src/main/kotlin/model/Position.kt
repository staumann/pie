package com.home.tool.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "positions")
data class Position(
    @Id @Column(name = "id") val id: String,
    @Column(name = "billId") val billID: String,
    @Column(name = "amount") val amount: Double,
    @Column(name = "name") val name: String,
    @Column(name = "targetId") val targetId: String,
    )