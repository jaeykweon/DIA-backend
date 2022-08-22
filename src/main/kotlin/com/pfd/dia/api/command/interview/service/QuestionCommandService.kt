package com.pfd.dia.api.command.interview.service

import com.pfd.dia.api.command.interview.dto.QuestionRequestData
import com.pfd.dia.api.command.interview.entity.QuestionEntity
import com.pfd.dia.api.command.interview.repository.QuestionEntityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = false)
class QuestionCommandService(
    private val questionEntityRepository: QuestionEntityRepository
) {
    // to-do: only admin can execute
    fun addQuestion(questionRequestData: QuestionRequestData) {
        val newQuestionEntity = with(questionRequestData) {
            QuestionEntity(
                indexId = indexId,
                question = question,
            )
        }
        questionEntityRepository.save(newQuestionEntity)
    }
}