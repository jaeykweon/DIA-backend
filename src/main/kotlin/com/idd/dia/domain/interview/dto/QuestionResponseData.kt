package com.idd.dia.domain.interview.dto

data class QuestionResponseData(
    val id: Long,
    val question: String,
    val firstCategory: String,
    val secondCategory: String,
    val answerId: Long,
)