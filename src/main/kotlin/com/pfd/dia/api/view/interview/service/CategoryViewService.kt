package com.pfd.dia.api.view.interview.service

import com.pfd.dia.api.view.interview.dto.CategoryView
import com.pfd.dia.api.view.interview.model.CategoryModel
import com.pfd.dia.api.view.interview.repository.CategoryModelRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CategoryViewService(
    private val categoryModelRepository: CategoryModelRepository,
) {
    fun getCategoryList(): List<CategoryView> {
        val categoryModelList: List<CategoryModel> = categoryModelRepository.findAll()
        return categoryModelList.map {
            CategoryView(
                id = it.id,
                firstCategory = it.firstCategory,
                secondCategory = it.secondCategory
            )
        }
    }
}
