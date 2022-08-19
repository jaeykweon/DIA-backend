package com.idd.dia.domain.interview.service

import com.idd.dia.domain.interview.dto.QuestionResponseData
import com.idd.dia.domain.index.service.IndexRepositoryService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QuestionQueryService(
    private val questionRepositoryService: QuestionRepositoryService,
    private val indexRepositoryService: IndexRepositoryService,
) {
    fun getQuestion(id: Long): QuestionResponseData {
        val questionEntity = questionRepositoryService.findById(id)
        val indexEntity = indexRepositoryService.findById(questionEntity.indexId)

        return QuestionResponseData(
            id = questionEntity.id,
            question = questionEntity.question,
            answerId = questionEntity.answerId,
            firstCategory = indexEntity.firstCategory,
            secondCategory = indexEntity.secondCategory,
        )
    }
}