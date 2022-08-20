package com.idd.dia.api.view.interview.model

import com.idd.dia.api.view.BaseModel
import org.hibernate.annotations.Immutable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Immutable
@Table(name = "view_question")
class QuestionModel(
    @Id
    @Column(name = "id")
    val id: Long,

    @Column(name = "first_category", nullable = false)
    val firstCategory: String,

    @Column(name = "second_category", nullable = false)
    val secondCategory: String,

    @Column(name = "question", nullable = false)
    val question: String,
): BaseModel()
