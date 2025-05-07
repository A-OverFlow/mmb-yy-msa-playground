package org.example.sample

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RequestLogService(
    private val requestLogRepository: RequestLogRepository
) {
    fun logRequestTime() {
        val requestLog = RequestLog(requestTime = LocalDateTime.now())
        requestLogRepository.save(requestLog)
    }
}
