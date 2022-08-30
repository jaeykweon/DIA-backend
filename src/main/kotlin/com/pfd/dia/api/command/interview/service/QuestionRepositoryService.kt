package com.pfd.dia.api.command.interview.service

import com.pfd.dia.api.DiaNotFoundException
import com.pfd.dia.api.command.interview.entity.QuestionEntity
import com.pfd.dia.api.command.interview.repository.QuestionEntityRepository
import com.pfd.dia.global.error.InterviewDomainError
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QuestionRepositoryService(
    private val questionEntityRepository: QuestionEntityRepository,
) {
    fun findById(id: Long) = questionEntityRepository.findByIdOrNull(id)
        ?: throw DiaNotFoundException(InterviewDomainError.QUESTION_ID_NOT_FOUND.message)

    fun findAll(): List<QuestionEntity> = questionEntityRepository.findAll()
}