package com.home.tool.model

data class Result(val resultEntries: Map<String, ResultEntry>)

data class ResultEntry(
    var name: String = "",
    var targeted: Double = 0.0,
    var total: Double? = null,
    var hasToPay: Boolean = false,
)
