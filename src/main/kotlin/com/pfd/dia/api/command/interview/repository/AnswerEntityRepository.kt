package com.pfd.dia.api.command.interview.repository

import com.pfd.dia.api.command.interview.entity.AnswerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AnswerEntityRepository: JpaRepository<AnswerEntity, Long> {
}