package org.example.sample.service

import org.example.sample.dto.AnswerCountResponse
import org.example.sample.dto.AnswerRequest
import org.example.sample.dto.AnswerResponse
import org.example.sample.dto.AnswerUpdateRequest
import org.example.sample.entity.Answer
import org.example.sample.repository.AnswerRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class AnswerService (
    private val answerRepository: AnswerRepository
){
    fun getAnswerAll(questionId: Long): List<AnswerResponse> {
        val sortByCreatedAtDesc = Sort.by(Sort.Direction.DESC, "createdAt")
        return answerRepository.findByQuestionId(questionId, sortByCreatedAtDesc)
            .map { AnswerResponse.fromEntity(it) };
    }

    @Transactional
    fun createAnswer(userId: Long, request: AnswerRequest): Long {
        val answer = Answer(
            questionId = request.questionId,
            answer = request.answer,
            author = "",
            userId = userId,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        return answerRepository.save(answer).answerId!!
    }

    @Transactional
    fun updateAnswer(userId: Long, answerId: Long, request: AnswerUpdateRequest){
        val answer = answerRepository.findById(answerId)
            .orElseThrow { IllegalArgumentException("답변이 존재하지 않습니다.") }

        if (answer.userId != userId) {
            throw IllegalAccessException("본인이 작성한 답변만 수정할 수 있습니다.")
        }

        answer.answer = request.answer
        answer.updatedAt = LocalDateTime.now()

        answerRepository.save(answer)
    }

    @Transactional
    fun deleteAnswer(userId: Long, answerId: Long){
        val answer = answerRepository.findById(answerId)
            .orElseThrow { IllegalArgumentException("답변이 존재하지 않습니다.") }

        if (answer.userId != userId) {
            throw IllegalAccessException("본인이 작성한 답변만 삭제할 수 있습니다.")
        }

        answerRepository.delete(answer)
    }

    @Transactional
    fun getRecentAnswers():List<AnswerResponse>{
        return answerRepository.findTop3ByOrderByCreatedAtDesc().map { AnswerResponse.fromEntity(it) };
    }

    @Transactional
    fun countAnswers(): AnswerCountResponse{
        return AnswerCountResponse(answerCount = answerRepository.count())
    }
}