package com.pfd.dia.api.view.interview.dto

data class QuestionView(
    val id: Long,
    val firstCategory: String,
    val secondCategory: String,
    val question: String,
)