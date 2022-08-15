package com.idd.dia.domain.exam.repository

import com.idd.dia.domain.exam.entity.QuestionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository: JpaRepository<QuestionEntity, Long> {
}