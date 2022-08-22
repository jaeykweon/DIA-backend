package com.idd.dia.api.command.user

import com.idd.dia.api.command.BaseEntity
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.Column
import javax.persistence.Entity
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
    githubUrl: String
): BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "identifier")
    var identifier: String = identifier
        protected set

    @Column(name = "github_url")
    var githubUrl: String = githubUrl
        protected set
}