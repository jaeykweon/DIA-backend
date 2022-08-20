package com.idd.dia.api.view.interview.repository

import com.idd.dia.api.view.interview.model.IndexModel
import org.springframework.data.jpa.repository.JpaRepository

interface IndexModelRepository: JpaRepository<IndexModel, Long> {
}