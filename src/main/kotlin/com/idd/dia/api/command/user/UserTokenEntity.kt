package com.idd.dia.api.command.user

import com.idd.dia.api.command.BaseEntity
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "tb_user_token")
@DynamicInsert
@DynamicUpdate
class UserTokenEntity(
    userEntity: UserEntity,
    accessToken: String,
    refreshToken: String,
): BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "tb_user_id", nullable = false)
    var user: UserEntity = userEntity
        protected set

    @Column(name = "access_token")
    var accessToken: String = accessToken
        protected set

    @Column(name = "refresh_token")
    var refreshToken: String = refreshToken
        protected set

}