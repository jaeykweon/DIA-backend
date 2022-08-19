package com.idd.dia.domain.interview.entity

import com.idd.dia.domain.BaseEntity
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tb_question")
@DynamicInsert
@DynamicUpdate
class QuestionEntity(
    question: String,
    IndexId: Long,
    modelAnswerId: Long
): BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0
        protected set

    @Column(name = "question", nullable = false)
    var question: String = question
        protected set

    @Column(name = "indexId", nullable = false)
    var indexId: Long = IndexId
        protected set

    @Column(name = "answer_id", nullable = false)
    var answerId: Long = modelAnswerId
        protected set
}