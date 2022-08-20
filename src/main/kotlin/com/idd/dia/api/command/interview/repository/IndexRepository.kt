package com.idd.dia.api.command.interview.repository

import com.idd.dia.api.command.interview.entity.IndexEntity
import org.springframework.data.jpa.repository.JpaRepository

interface IndexRepository: JpaRepository<IndexEntity, Long> {
}