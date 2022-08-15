package com.idd.dia.domain.index

import org.springframework.data.jpa.repository.JpaRepository

interface IndexRepository: JpaRepository<IndexEntity, Long> {
}