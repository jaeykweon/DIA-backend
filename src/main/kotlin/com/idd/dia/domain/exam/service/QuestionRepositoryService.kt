package com.idd.dia.domain.exam.service

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
        ?: throw NoSuchElementException("없는 문항 번호입니다.")
}