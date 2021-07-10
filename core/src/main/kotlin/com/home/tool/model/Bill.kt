package com.home.tool.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "bills")
data class Bill(
    @Id @Column(name = "id") var id: String = "",
    @Column(name = "payed_by") var payedBy: String = "",
    @Column(name = "shop_id") var shopId: String = "",
    @Column(name = "discount") var discount: Double? = 0.0,
    @Column(name = "billing_date") @JsonFormat(pattern="dd.MM.yyyy") @Temporal(TemporalType.DATE)var date: Calendar = Calendar.getInstance()
)

data class DisplayBill(val id: String, val payedBy: User?, val total: Double, val date: String, val shop: Shop?)