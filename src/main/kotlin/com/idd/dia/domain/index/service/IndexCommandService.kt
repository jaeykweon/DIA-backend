package com.idd.dia.domain.index.service

import com.idd.dia.domain.index.IndexEntity
import com.idd.dia.domain.index.IndexRepository
import com.idd.dia.domain.index.dto.IndexRequestData
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = false)
class IndexCommandService(
    private val indexRepository: IndexRepository
) {
    // to-do: only admin can execute
    fun addIndex(indexRequestData: IndexRequestData) {
        val newIndexEntity = with(indexRequestData) {
            IndexEntity(
                firstCategory = firstCategory,
                secondCategory = secondCategory
            )
        }
        indexRepository.save(newIndexEntity)
    }
}