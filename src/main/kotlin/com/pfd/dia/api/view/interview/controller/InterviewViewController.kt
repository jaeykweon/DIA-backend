package com.pfd.dia.api.view.interview.controller

import com.pfd.dia.api.ApiResponse
import com.pfd.dia.api.view.interview.dto.CategoryView
import com.pfd.dia.api.view.interview.dto.QuestionView
import com.pfd.dia.api.view.interview.service.CategoryViewService
import com.pfd.dia.api.view.interview.service.QuestionViewService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/interviews")
class InterviewViewController(
    private val categoryViewService: CategoryViewService,
    private val questionViewService: QuestionViewService,
) {

    @GetMapping("/categories")
    fun getCategoryList(): ApiResponse<List<CategoryView>> {
        val categoryViewList = categoryViewService.getCategoryList()
        return ApiResponse.success(categoryViewList)
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