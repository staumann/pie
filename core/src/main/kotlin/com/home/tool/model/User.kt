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
@Table(name = "users")
data class User(
    @Id @Column(name = "id") var id: String = "",
    @Column(name = "first_name") var firstName: String = "",
    @Column(name = "last_name") var lastName: String = "",
    @Column(name = "created_at")
    @JsonFormat(pattern = "dd.MM.yyyy")
    @Temporal(TemporalType.DATE)
    var date: Calendar = Calendar.getInstance(),
    @Column
    var pool: Boolean = false
)
