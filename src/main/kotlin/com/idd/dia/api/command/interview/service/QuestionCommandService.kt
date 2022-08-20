package com.idd.dia.api.command.interview.service

import com.idd.dia.api.command.interview.dto.QuestionRequestData
import com.idd.dia.api.command.interview.entity.QuestionEntity
import com.idd.dia.api.command.interview.repository.QuestionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = false)
class QuestionCommandService(
    private val questionRepository: QuestionRepository
) {
    // to-do: only admin can execute
    fun addQuestion(questionRequestData: QuestionRequestData) {
        val newQuestionEntity = with(questionRequestData) {
            QuestionEntity(
                indexId = indexId,
                question = question,
            )
        }
        questionRepository.save(newQuestionEntity)
    }
}