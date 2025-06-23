package org.example.sample.repository

import org.example.sample.dto.AnswerResponse
import org.example.sample.entity.Answer
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository

interface AnswerRepository: JpaRepository<Answer, Long>{
    fun findByQuestionId(questionId: Long, sort: Sort): List<Answer>

    fun findTop5ByOrderByCreatedAtDesc(): List<Answer>
}