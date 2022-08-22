package com.pfd.dia.api.view.interview.service

import com.pfd.dia.api.view.interview.dto.IndexView
import com.pfd.dia.api.view.interview.model.IndexModel
import com.pfd.dia.api.view.interview.repository.IndexModelRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class IndexViewService(
    private val indexModelRepository: IndexModelRepository,
) {
    fun getIndexList(): List<IndexView> {
        val indexes: List<IndexModel> = indexModelRepository.findAll()
        return indexes.map {
            IndexView(
                id = it.id,
                firstCategory = it.firstCategory,
                secondCategory = it.secondCategory
            )
        }
    }
}
