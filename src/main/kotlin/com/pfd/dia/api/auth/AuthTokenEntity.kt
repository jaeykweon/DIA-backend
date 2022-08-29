package com.pfd.dia.api.auth

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
@Table(name = "tb_auth_token")
@DynamicInsert
@DynamicUpdate
class AuthTokenEntity(
    userId: Long,
    accessToken: String,
    refreshToken: String,
): BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "user_id")
    var userId: Long = userId
        protected set

    @Column(columnDefinition ="TEXT", name = "access_token")
    var accessToken: String = accessToken
        protected set

    @Column(columnDefinition ="TEXT", name = "refresh_token")
    var refreshToken: String = refreshToken
        protected set

}