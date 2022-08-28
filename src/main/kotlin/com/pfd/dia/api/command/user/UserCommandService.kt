package com.pfd.dia.api.command.user

import com.pfd.dia.api.command.user.constant.UserSource
import com.pfd.dia.api.command.user.entity.UserEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = false)
class UserCommandService(
    private val userEntityRepository: UserEntityRepository,
) {
    fun registerUser(identifier: String, from: String, githubUrl: String, profileImageUrl: String): Long {
        val userSource = UserSource.from(from)
        val newUser = UserEntity(
            identifier= identifier,
            userSource = userSource,
            githubUrl = githubUrl,
            profileImageUrl = profileImageUrl
        )
        val savedUser = userEntityRepository.save(newUser)
        return savedUser.id
    }
}