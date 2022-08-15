package com.idd.dia.domain

import java.util.Date
import javax.persistence.MappedSuperclass
import javax.persistence.PreUpdate

@MappedSuperclass
abstract class BaseEntity {

    var createdAt: Date = Date()
    var updatedAt: Date? = null

    @PreUpdate
    fun preUpdate() {
        updatedAt = Date()
    }
}
