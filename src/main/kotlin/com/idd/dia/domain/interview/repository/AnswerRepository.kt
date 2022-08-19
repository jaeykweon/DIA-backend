package com.idd.dia.domain.interview.repository

import com.idd.dia.domain.interview.entity.AnswerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AnswerRepository: JpaRepository<AnswerEntity, Long> {
}