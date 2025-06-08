package org.example.sample.dto

import org.example.sample.entity.Answer
import java.time.LocalDateTime

data class AnswerResponse(
    val answerId: Long,
    val questionId: Long,
    val answer: String,
    val author: String,
    val userId: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun fromEntity(answer: Answer): AnswerResponse =
            AnswerResponse(
                answerId = answer.answerId!!,
                questionId = answer.questionId!!,
                answer = answer.answer,
                author = answer.author,
                userId = answer.userId,
                createdAt = answer.createdAt,
                updatedAt = answer.updatedAt
            )
    }
}

