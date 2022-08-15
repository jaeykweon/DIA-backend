package com.idd.dia.domain.exam.service

import com.idd.dia.domain.exam.dto.QuestionResponseData
import com.idd.dia.domain.exam.entity.QuestionEntity
import com.idd.dia.domain.exam.repository.QuestionRepository
import com.idd.dia.domain.index.IndexRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QuestionQueryService(
    private val questionRepository: QuestionRepository,
    private val indexRepository: IndexRepository
) {
    fun getQuestion(id: Long): QuestionResponseData {
        val questionEntity: QuestionEntity = questionRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("없는 문항 번호입니다.")
        val indexEntity = indexRepository.findByIdOrNull(questionEntity.indexId)
            ?: throw IllegalArgumentException("문항의 목차 번호가 올바르지 않습니다.")

        return QuestionResponseData(
            id = questionEntity.id,
            question = questionEntity.question,
            answerId = questionEntity.answerId,
            firstCategory = indexEntity.firstCategory,
            secondCategory = indexEntity.secondCategory,
        )
    }
}