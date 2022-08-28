package com.pfd.dia.api.command.user

import com.pfd.dia.api.command.user.constant.UserSource
import com.pfd.dia.api.command.user.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserEntityRepository: JpaRepository<UserEntity, Long> {
    fun findByIdentifierAndUserSource(identifier: String, userSource: UserSource): UserEntity?
}