package com.idd.dia.api.command.interview.service

import com.idd.dia.api.command.interview.entity.QuestionEntity
import com.idd.dia.api.command.interview.repository.QuestionRepository
import com.idd.dia.global.error.InterviewDomainError
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QuestionRepositoryService(
    private val questionRepository: QuestionRepository,
) {
    fun findById(id: Long) = questionRepository.findByIdOrNull(id)
        ?: throw NoSuchElementException(InterviewDomainError.QUESTION_ID_NOT_FOUND.message)

    fun findAll(): List<QuestionEntity> = questionRepository.findAll()
}