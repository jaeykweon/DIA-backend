package com.pfd.dia.api.command.interview.service

import com.pfd.dia.api.DiaNotFoundException
import com.pfd.dia.global.error.InterviewDomainError.INDEX_ID_NOT_FOUND
import com.pfd.dia.api.command.interview.entity.IndexEntity
import com.pfd.dia.api.command.interview.repository.IndexEntityRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class IndexRepositoryService(
    private val indexEntityRepository: IndexEntityRepository
) {
    fun findById(id: Long) = indexEntityRepository.findByIdOrNull(id)
        ?: throw DiaNotFoundException(INDEX_ID_NOT_FOUND.message)

    fun findAll(): List<IndexEntity> = indexEntityRepository.findAll()
}