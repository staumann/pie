package com.home.tool.model

data class Result(val resultEntries: Map<String, ResultEntry>)

data class  ResultEntry(val total: Double)
