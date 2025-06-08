package org.example.sample.repository

import org.example.sample.entity.RequestLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RequestLogRepository : JpaRepository<RequestLog, Long>