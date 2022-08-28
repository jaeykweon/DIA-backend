package com.pfd.dia.api.auth

import org.springframework.data.jpa.repository.JpaRepository

interface AuthTokenRepository: JpaRepository<AuthTokenEntity, Long> {
}