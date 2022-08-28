package com.pfd.dia.api.command.user

import com.pfd.dia.api.command.user.constant.UserSource
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserReaderService(
    private val userEntityRepository: UserEntityRepository
) {
    fun findUserId(identifier: String, from: String): Long? {
        val userSource = UserSource.from(from)
        return userEntityRepository.findByIdentifierAndUserSource(identifier, userSource)?.id
    }
}