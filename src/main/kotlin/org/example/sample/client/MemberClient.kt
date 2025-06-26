package org.example.sample.client

import org.example.sample.dto.Author
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "member-service", url = "http://mmb-member-service:8082/api/v1")
interface MemberClient {
    @GetMapping("/members/me")
    fun getAuthor(@RequestHeader("X-User-Id") authorId: Long): Author
}