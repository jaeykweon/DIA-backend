package com.idd.dia.api.command.interview.repository

import com.idd.dia.api.command.interview.entity.QuestionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository: JpaRepository<QuestionEntity, Long> {
}