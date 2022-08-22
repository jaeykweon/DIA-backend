package com.pfd.dia.api.view

import java.util.Date
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseModel {
    var createdAt: Date? = null
    var updatedAt: Date? = null
}

