package com.idd.dia.domain.index.service

import com.idd.dia.domain.index.dto.IndexResponseData
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class IndexQueryService(
    private val indexRepositoryService: IndexRepositoryService
) {
    fun getIndexList(): List<IndexResponseData> {
        val indexes = indexRepositoryService.findAll()
        return indexes.map {
            IndexResponseData(
                id = it.id,
                firstCategory = it.firstCategory,
                secondCategory = it.secondCategory
            )
        }
    }
}
