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
@Table(name = "tb_question")
@DynamicInsert
@DynamicUpdate
class QuestionEntity(
    categoryId: Long,
    question: String,
): BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "category_id", nullable = false)
    var categoryId: Long = categoryId
        protected set

    @Column(name = "question", nullable = false)
    var question: String = question
        protected set
}