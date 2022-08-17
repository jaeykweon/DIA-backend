package com.idd.dia.domain.index.service

import com.idd.dia.domain.index.IndexEntity
import com.idd.dia.domain.index.IndexRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class IndexRepositoryService(
    private val indexRepository: IndexRepository
) {
    fun findById(id: Long) = indexRepository.findByIdOrNull(id)
        ?: throw NoSuchElementException("없는 목차 번호입니다.")

    fun findAll(): List<IndexEntity> = indexRepository.findAll()
}