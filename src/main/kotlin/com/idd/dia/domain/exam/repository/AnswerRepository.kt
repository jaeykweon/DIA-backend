package com.idd.dia.domain.exam.repository

import com.idd.dia.domain.exam.entity.AnswerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AnswerRepository: JpaRepository<AnswerEntity, Long> {
}