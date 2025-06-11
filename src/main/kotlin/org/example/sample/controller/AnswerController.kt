package org.example.sample.controller

import org.example.sample.dto.AnswerCountResponse
import org.example.sample.dto.AnswerRequest
import org.example.sample.dto.AnswerResponse
import org.example.sample.dto.AnswerUpdateRequest
import org.example.sample.service.AnswerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/v1")
class AnswerController(
    private val answerService: AnswerService
){

    /**
     * 질문의 전체 답변 조회
     * @param
     * @return
     * */
    @GetMapping("/answers/{questionId}")
    fun getAllAnswers(
        @PathVariable questionId: Long
    ): ResponseEntity<List<AnswerResponse>> {
        val answers = answerService.getAnswerAll(questionId);
        return ResponseEntity.ok(answers);
    }

    /**
     * 답변 생성
     * @param
     * @return
     * */
    @PostMapping("/answers")
    fun createAnswer(
        @RequestHeader("X-User-Id") userId: Long,
        @RequestBody request: AnswerRequest
    ): ResponseEntity<Void> {
        val answerId = answerService.createAnswer(userId, request)
        val location = URI.create("/api/v1/answers/$answerId")
        return ResponseEntity.created(location).build<Void>()
    }

    /**
     * 답변 수정
     * @param
     * @return
     * */
    @PatchMapping("/answers/{answerId}")
    fun updateAnswer(
        @RequestHeader("X-USER-ID") userId: Long,
        @PathVariable answerId: Long,
        @RequestBody request: AnswerUpdateRequest
    ): ResponseEntity<Void> {
        answerService.updateAnswer(userId, answerId, request)
        return ResponseEntity.noContent().build<Void>()
    }

    /**
     * 답변 삭제
     * @param
     * @return
     * */
    @DeleteMapping("/answers/{answerId}")
    fun deleteAnswer(
        @RequestHeader("X-USER-ID") userId: Long,
        @PathVariable answerId: Long
    ):ResponseEntity<Void> {
        answerService.deleteAnswer(userId, answerId);
        return ResponseEntity.noContent().build<Void>();
    }

    /**
     * 최근 답변 조회 (최대 3)
     * @return 답변 목록
     * */
    @GetMapping("/answers/recent")
    fun recentAnswer(
    ):ResponseEntity<List<AnswerResponse>>{
        return ResponseEntity.ok(answerService.getRecentAnswers())
    }

    /**
     * 전체 답변 수
     * @return 전체 답변 수
     * */
    @GetMapping("/answers/count")
    fun countAnswers(
    ): ResponseEntity<AnswerCountResponse>{
        return ResponseEntity.ok(answerService.countAnswers())
    }

}