package com.home.tool.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.Calendar
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.TemporalType
import javax.persistence.Temporal

@Entity
@Table(name = "bills")
data class Bill(
    @Id @Column(name = "id") var id: String = "",
    @Column(name = "payed_by") var payedBy: String = "",
    @Column(name = "shop_id") var shopId: String = "",
    @Column(name = "billing_date")
    @JsonFormat(pattern = "dd.MM.yyyy")
    @Temporal(TemporalType.DATE)
    var date: Calendar = Calendar.getInstance()
)

data class DisplayBill(
    val id: String,
    val payedBy: User?,
    val total: Double,
    val date: String,
    val shop: Shop?,
    val positions: Iterable<Position>?,
    val result: Iterable<ResultEntry>
)
