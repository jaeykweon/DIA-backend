package com.idd.dia.domain.interview

import com.idd.dia.domain.ApiResponse
import com.idd.dia.domain.interview.dto.AnswerResponseData
import com.idd.dia.domain.interview.dto.QuestionResponseData
import com.idd.dia.domain.interview.service.AnswerQueryService
import com.idd.dia.domain.interview.service.QuestionQueryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/exams")
class InterviewController(
    private val questionQueryService: QuestionQueryService,
    private val answerQueryService: AnswerQueryService
) {
    @GetMapping("/questions/{questionId}")
    fun getQuestion(
        @PathVariable questionId: Long
    ): ApiResponse<QuestionResponseData> {
        return ApiResponse.success(
            data = questionQueryService.getQuestion(questionId)
        )
    }

    @GetMapping("/answers/{answerId}")
    fun getAnswer(
        @PathVariable answerId: Long
    ): ApiResponse<AnswerResponseData> {
        return ApiResponse.success(
            data = answerQueryService.getAnswer(answerId)
        )
    }

}