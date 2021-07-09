package com.home.tool.service

import java.sql.Timestamp
import java.time.Instant

fun getIdForString(prefix: String): String = "${prefix.toLowerCase()}-${System.currentTimeMillis()/1000}"

fun getTimeStamp(): String {
    return Timestamp.from(Instant.now()).toString()
}