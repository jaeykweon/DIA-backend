package com.pfd.dia.api.command.interview.entity

import com.pfd.dia.api.command.BaseEntity
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tb_category")
@DynamicInsert
@DynamicUpdate
class CategoryEntity(
    firstCategory: String,
    secondCategory: String
): BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "first_category", nullable = false)
    var firstCategory: String = firstCategory
        protected set

    @Column(name = "second_category", nullable = false)
    var secondCategory: String = secondCategory
        protected set
}