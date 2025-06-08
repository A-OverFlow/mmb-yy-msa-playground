package org.example.sample.entity

import jakarta.persistence.*
import java.time.LocalDateTime
@Entity
@Table(name = "answer")
class Answer(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val answerId: Long? = null,

    @Column(nullable = false)
    val questionId: Long,

    @Column(nullable = false, columnDefinition = "TEXT")
    var answer: String,

    @Column(nullable = false)
    var author: String,

    @Column(nullable = false)
    val userId: Long,

    @Column(nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()

) {
    // JPA 기본 생성자
    protected constructor() : this(questionId = 0, answer = "", author = "", userId = 0, createdAt = LocalDateTime.now(), updatedAt = LocalDateTime.now())
}

