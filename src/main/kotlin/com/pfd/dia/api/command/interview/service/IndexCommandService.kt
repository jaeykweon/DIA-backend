package com.pfd.dia.api.command.interview.service

import com.pfd.dia.api.command.interview.dto.IndexRequestData
import com.pfd.dia.api.command.interview.entity.IndexEntity
import com.pfd.dia.api.command.interview.repository.IndexEntityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = false)
class IndexCommandService(
    private val indexEntityRepository: IndexEntityRepository
) {
    // to-do: only admin can execute
    fun addIndex(indexRequestData: IndexRequestData) {
        val newIndexEntity = with(indexRequestData) {
            IndexEntity(
                firstCategory = firstCategory,
                secondCategory = secondCategory
            )
        }
        indexEntityRepository.save(newIndexEntity)
    }
}