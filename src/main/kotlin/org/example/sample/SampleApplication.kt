package org.example.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients(basePackages = ["org.example.sample.client"])

class SampleApplication

fun main(args: Array<String>) {
    runApplication<SampleApplication>(*args)
}
