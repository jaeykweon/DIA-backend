package com.idd.dia.api.view.interview.repository

import com.idd.dia.api.view.interview.model.QuestionModel
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionModelRepository: JpaRepository<QuestionModel, Long> {
}