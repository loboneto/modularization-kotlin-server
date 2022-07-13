package com.example.data.entity

import org.ktorm.entity.Entity

interface UserDao : Entity<UserDao> {

    companion object : Entity.Factory<UserDao>()

    val cpf: String
    val name: String
    val createdAt: Long

}
