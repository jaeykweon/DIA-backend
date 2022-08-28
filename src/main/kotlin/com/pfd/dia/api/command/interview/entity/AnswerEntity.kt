package com.pfd.dia.api.command.interview.entity

import com.pfd.dia.api.command.BaseEntity
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tb_answer")
@DynamicInsert
@DynamicUpdate
class AnswerEntity(
    questionId: Long,
    answer: String
): BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "question_id", nullable = false)
    var questionId: Long = questionId
        protected set

    @Column(name = "model_answer", nullable = false)
    var answer: String = answer
        protected set
}