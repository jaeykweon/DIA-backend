package com.idd.dia.api.command.interview.repository

import com.idd.dia.api.command.interview.entity.IndexEntity
import org.springframework.data.jpa.repository.JpaRepository

interface IndexEntityRepository: JpaRepository<IndexEntity, Long> {
}