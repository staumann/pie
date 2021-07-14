package com.home.tool

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class HomeApplication

/**
 * the main function
 */
fun main(args: Array<String>) {
    runApplication<HomeApplication>(args.joinToString { " " })
}
