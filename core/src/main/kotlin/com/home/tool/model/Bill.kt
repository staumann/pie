package com.home.tool.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "bills")
data class Bill(
    @Id @Column(name = "id") var id: String = "",
    @Column(name = "payedBy") var payedBy: String = "",
    @Column(name = "shop_id") var shopId: String = "",
    @Column(name = "billing_date") @JsonFormat(pattern="dd.MM.yyyy") @Temporal(TemporalType.DATE)var date: Calendar = Calendar.getInstance()
)