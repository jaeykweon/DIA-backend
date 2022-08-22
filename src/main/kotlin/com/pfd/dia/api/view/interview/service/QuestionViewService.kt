package com.pfd.dia.api.view.interview.service

import com.pfd.dia.global.error.InterviewDomainError.QUESTION_ID_NOT_FOUND
import com.pfd.dia.api.view.interview.dto.QuestionView
import com.pfd.dia.api.view.interview.repository.QuestionModelRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QuestionViewService(
    private val questionModelRepository: QuestionModelRepository,
) {
    fun getQuestion(id: Long): QuestionView {
        val questionModel = questionModelRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException(QUESTION_ID_NOT_FOUND.message)

        return QuestionView(
            id = questionModel.id,
            question = questionModel.question,
            firstCategory = questionModel.firstCategory,
            secondCategory = questionModel.secondCategory,
        )
    }
}