package com.pfd.dia.api.command.interview.repository

import com.pfd.dia.api.command.interview.entity.QuestionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionEntityRepository: JpaRepository<QuestionEntity, Long> {
}