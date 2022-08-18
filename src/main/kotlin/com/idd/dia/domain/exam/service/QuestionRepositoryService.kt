package com.idd.dia.domain.exam.service

import com.idd.dia.domain.exam.ExamDomainError.QUESTION_ID_NOT_FOUND
import com.idd.dia.domain.exam.repository.QuestionRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QuestionRepositoryService(
    private val questionRepository: QuestionRepository,
) {
    fun findById(id: Long) = questionRepository.findByIdOrNull(id)
        ?: throw NoSuchElementException(QUESTION_ID_NOT_FOUND.message)
}