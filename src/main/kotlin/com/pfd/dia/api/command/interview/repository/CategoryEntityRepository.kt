package com.pfd.dia.api.command.interview.repository

import com.pfd.dia.api.command.interview.entity.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryEntityRepository: JpaRepository<CategoryEntity, Long> {
}