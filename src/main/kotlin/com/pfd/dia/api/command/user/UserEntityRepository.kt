package com.pfd.dia.api.command.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserEntityRepository: JpaRepository<UserEntity, Long> {
}