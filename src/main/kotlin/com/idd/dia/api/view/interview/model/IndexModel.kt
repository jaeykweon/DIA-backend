package com.idd.dia.api.view.interview.model

import com.idd.dia.api.view.BaseModel
import org.hibernate.annotations.Immutable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Immutable
@Table(name = "view_index")
class IndexModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long,

    @Column(name = "first_category", nullable = false)
    val firstCategory: String,

    @Column(name = "second_category", nullable = false)
    val secondCategory: String,


): BaseModel()