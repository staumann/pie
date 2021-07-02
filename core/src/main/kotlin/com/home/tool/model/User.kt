package com.home.tool.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id @Column(name = "id") val id: String = "",
    @Column(name = "first_name") val firstName: String = "",
    @Column(name = "last_name") val lastName: String = "",
    @Column(name = "created_at") @JsonFormat(pattern="dd.MM.yyyy") @Temporal(TemporalType.DATE)var date: Calendar = Calendar.getInstance()
)