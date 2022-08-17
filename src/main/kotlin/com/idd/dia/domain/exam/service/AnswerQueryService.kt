package com.idd.dia.domain.exam.service

import com.idd.dia.domain.exam.dto.AnswerResponseData
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AnswerQueryService(
    private val answerRepositoryService: AnswerRepositoryService
) {
    fun getAnswer(id: Long): AnswerResponseData {
        val answerEntity = answerRepositoryService.findById(id)

        return AnswerResponseData(
            id = answerEntity.id,
            modelAnswer = answerEntity.modelAnswer
        )
    }
}