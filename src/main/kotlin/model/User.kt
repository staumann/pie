package com.home.tool.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class User(
    @Id @Column(name = "id") val id: String = "",
    @Column(name = "firstName") val firstName: String = "",
    @Column(name = "lastName") val lastName: String = "")