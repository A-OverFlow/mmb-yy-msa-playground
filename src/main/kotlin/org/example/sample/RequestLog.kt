package org.example.sample

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "request_log")
class RequestLog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "request_time", nullable = false)
    val requestTime: LocalDateTime = LocalDateTime.now()
)