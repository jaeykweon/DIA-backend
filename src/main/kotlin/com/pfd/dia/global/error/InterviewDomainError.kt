package com.pfd.dia.global.error

enum class InterviewDomainError(val message: String) {
    INDEX_ID_NOT_FOUND("없는 목차 번호입니다."),
    QUESTION_ID_NOT_FOUND("없는 문항 번호입니다."),
    ANSWER_ID_NOT_FOUND("없는 답안 번호입니다."),
}