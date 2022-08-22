package com.idd.dia.api.command.interview.entity

import com.idd.dia.api.command.BaseEntity
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
    indexId: Long,
    question: String,
): BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "indexId", nullable = false)
    var indexId: Long = indexId
        protected set

    @Column(name = "question", nullable = false)
    var question: String = question
        protected set
}