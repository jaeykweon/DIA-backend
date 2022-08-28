package com.pfd.dia.api.command.user.entity

import com.pfd.dia.api.command.BaseEntity
import com.pfd.dia.api.command.user.constant.UserSource
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tb_user")
@DynamicInsert
@DynamicUpdate
class UserEntity(
    identifier: String,
    userSource: UserSource,
    githubUrl: String,
    profileImageUrl: String,
): BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "identifier")
    var identifier: String = identifier
        protected set

    @Enumerated(EnumType.STRING)
    var userSource: UserSource = userSource
        protected set

    @Column(name = "github_url")
    var githubUrl: String = githubUrl
        protected set

    @Column(name = "profile_image_url")
    var profileImageUrl: String = profileImageUrl
        protected set
}