package com.pfd.dia.api.view.interview.repository

import com.pfd.dia.api.view.interview.model.CategoryModel
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryModelRepository: JpaRepository<CategoryModel, Long> {
}