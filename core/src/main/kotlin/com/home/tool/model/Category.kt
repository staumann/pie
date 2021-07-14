package com.home.tool.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "categories")
data class Category(
    @Id @Column(name = "id") var id: String = "",
    @Column(name = "name") var name: String = "",
    @Column(name = "description") var description: String = ""
)
