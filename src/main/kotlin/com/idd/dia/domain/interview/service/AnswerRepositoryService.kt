package com.idd.dia.domain.interview.service

import com.idd.dia.domain.interview.InterviewDomainError.ANSWER_ID_NOT_FOUND
import com.idd.dia.domain.interview.repository.AnswerRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AnswerRepositoryService(
    private val answerRepository: AnswerRepository
) {
    fun findById(id: Long) = answerRepository.findByIdOrNull(id)
        ?: throw IllegalArgumentException(ANSWER_ID_NOT_FOUND.message)
}