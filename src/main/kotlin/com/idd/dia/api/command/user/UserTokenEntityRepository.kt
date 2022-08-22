package com.idd.dia.api.command.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserTokenEntityRepository: JpaRepository<UserTokenEntityRepository, Long> {
}