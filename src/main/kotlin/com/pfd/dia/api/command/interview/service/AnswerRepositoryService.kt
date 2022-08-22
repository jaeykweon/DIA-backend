package com.pfd.dia.api.command.interview.service

import com.pfd.dia.api.command.interview.repository.AnswerEntityRepository
import com.pfd.dia.global.error.InterviewDomainError.ANSWER_ID_NOT_FOUND
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AnswerRepositoryService(
    private val answerEntityRepository: AnswerEntityRepository
) {
    fun findById(id: Long) = answerEntityRepository.findByIdOrNull(id)
        ?: throw IllegalArgumentException(ANSWER_ID_NOT_FOUND.message)
}