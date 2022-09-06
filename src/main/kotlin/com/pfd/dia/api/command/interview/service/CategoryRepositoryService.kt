package com.pfd.dia.api.command.interview.service

import com.pfd.dia.api.DiaNotFoundException
import com.pfd.dia.global.error.InterviewDomainError.CATEGORY_ID_NOT_FOUND
import com.pfd.dia.api.command.interview.entity.CategoryEntity
import com.pfd.dia.api.command.interview.repository.CategoryEntityRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CategoryRepositoryService(
    private val categoryEntityRepository: CategoryEntityRepository
) {
    fun findById(id: Long) = categoryEntityRepository.findByIdOrNull(id)
        ?: throw DiaNotFoundException(CATEGORY_ID_NOT_FOUND.message)

    fun findAll(): List<CategoryEntity> = categoryEntityRepository.findAll()
}