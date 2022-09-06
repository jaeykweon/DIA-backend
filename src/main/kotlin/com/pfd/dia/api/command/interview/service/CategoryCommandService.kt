package com.pfd.dia.api.command.interview.service

import com.pfd.dia.api.command.interview.dto.CategoryRequestData
import com.pfd.dia.api.command.interview.entity.CategoryEntity
import com.pfd.dia.api.command.interview.repository.CategoryEntityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = false)
class CategoryCommandService(
    private val categoryEntityRepository: CategoryEntityRepository
) {
    // to-do: only admin can execute
    fun addCategory(categoryRequestData: CategoryRequestData) {
        val newCategoryEntity = with(categoryRequestData) {
            CategoryEntity(
                firstCategory = firstCategory,
                secondCategory = secondCategory
            )
        }
        categoryEntityRepository.save(newCategoryEntity)
    }
}