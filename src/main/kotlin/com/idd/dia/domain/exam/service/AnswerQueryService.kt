package com.idd.dia.domain.exam.service

import com.idd.dia.domain.exam.dto.AnswerResponseData
import com.idd.dia.domain.exam.repository.AnswerRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AnswerQueryService(
    private val answerRepository: AnswerRepository
) {
    fun getAnswer(id: Long): AnswerResponseData {
        val answerEntity = answerRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("없는 답안 번호입니다.")

        return AnswerResponseData(
            id = answerEntity.id,
            modelAnswer = answerEntity.modelAnswer
        )
    }
}