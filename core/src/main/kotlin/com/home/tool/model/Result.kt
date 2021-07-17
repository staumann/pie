package com.home.tool.model

data class Result(val resultEntries: Map<String, ResultEntry>)

data class ResultEntry(
    val name: String = "",
    val payed: Double = 0.0,
    var total: Double? = null
)
