package com.idd.dia.api.command.interview.service

import com.idd.dia.api.command.interview.dto.IndexRequestData
import com.idd.dia.api.command.interview.entity.IndexEntity
import com.idd.dia.api.command.interview.repository.IndexRepository
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