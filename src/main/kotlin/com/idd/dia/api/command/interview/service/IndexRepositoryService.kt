package com.idd.dia.api.command.interview.service

import com.idd.dia.global.error.InterviewDomainError.INDEX_ID_NOT_FOUND
import com.idd.dia.api.command.interview.entity.IndexEntity
import com.idd.dia.api.command.interview.repository.IndexEntityRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class IndexRepositoryService(
    private val indexEntityRepository: IndexEntityRepository
) {
    fun findById(id: Long) = indexEntityRepository.findByIdOrNull(id)
        ?: throw NoSuchElementException(INDEX_ID_NOT_FOUND.message)

    fun findAll(): List<IndexEntity> = indexEntityRepository.findAll()
}