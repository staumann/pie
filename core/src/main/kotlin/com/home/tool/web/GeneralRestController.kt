package com.home.tool.web

import com.home.tool.model.Response
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class GeneralRestController {
    @GetMapping("/health")
    fun healthCheck(): Response {
        return Response(true, "service is up and running")
    }
}