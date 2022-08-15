package com.idd.dia.domain.index.service

import com.idd.dia.domain.index.IndexRepository
import com.idd.dia.domain.index.dto.IndexResponseData
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class IndexQueryService(
    private val indexRepository: IndexRepository
) {
    fun getIndexList(): List<IndexResponseData> {
        val indexes = indexRepository.findAll()
        return indexes.map {
            IndexResponseData(
                id = it.id,
                firstCategory = it.firstCategory,
                secondCategory = it.secondCategory
            )
        }
    }
}
