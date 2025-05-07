package org.example.sample

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(
    private val requestLogService: RequestLogService
) {

    @GetMapping("/hello")
    fun hello(): String {
        requestLogService.logRequestTime()
        return "hello world"
    }
}