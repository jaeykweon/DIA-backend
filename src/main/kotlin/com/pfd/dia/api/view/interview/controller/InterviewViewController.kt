package com.pfd.dia.api.view.interview.controller

import com.pfd.dia.api.ApiResponse
import com.pfd.dia.api.view.interview.dto.IndexView
import com.pfd.dia.api.view.interview.dto.QuestionView
import com.pfd.dia.api.view.interview.service.IndexViewService
import com.pfd.dia.api.view.interview.service.QuestionViewService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/interviews")
class InterviewViewController(
    private val indexViewService: IndexViewService,
    private val questionViewService: QuestionViewService,
) {

    @GetMapping("/indexes")
    fun getIndexList(): ApiResponse<List<IndexView>> {
        val indexList = indexViewService.getIndexList()
        return ApiResponse.success(indexList)
    }

    @GetMapping("/questions/{questionId}")
    fun getQuestion(
        @PathVariable questionId: Long
    ): ApiResponse<QuestionView> {
        return ApiResponse.success(
            data = questionViewService.getQuestion(questionId)
        )
    }
}