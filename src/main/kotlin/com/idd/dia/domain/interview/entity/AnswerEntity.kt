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
@Table(name = "tb_answer")
@DynamicInsert
@DynamicUpdate
class AnswerEntity(
    modelAnswer: String
): BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0
        protected set

    @Column(name = "model_answer", nullable = false)
    var modelAnswer: String = modelAnswer
        protected set
}