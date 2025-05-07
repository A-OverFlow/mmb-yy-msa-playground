package org.example.sample

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RequestLogRepository : JpaRepository<RequestLog, Long>