package com.home.tool.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "positions")
data class Position(
    @Id @Column(name = "id") var id: String = "",
    @Column(name = "bill_id") var billID: String = "",
    @Column(name = "amount") var amount: Double = 0.0,
    @Column(name = "name") var name: String = "",
    @Column(name = "target_id") var targetId: String = "",
    @Column(name = "category_id") var category: String = ""
    )