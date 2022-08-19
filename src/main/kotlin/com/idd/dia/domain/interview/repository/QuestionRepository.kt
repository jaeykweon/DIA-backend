package com.idd.dia.domain.interview.repository

import com.idd.dia.domain.interview.entity.QuestionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository: JpaRepository<QuestionEntity, Long> {
}