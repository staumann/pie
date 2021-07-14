package com.home.tool.service

import java.sql.Timestamp
import java.time.Instant

const val FACTOR: Int = 1000

fun getIdForString(prefix: String): String = "${prefix.toLowerCase()}-${System.currentTimeMillis()/FACTOR}"

fun getTimeStamp(): String {
    return Timestamp.from(Instant.now()).toString()
}
