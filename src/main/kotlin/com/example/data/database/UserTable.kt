package com.example.data.database

import com.example.data.entity.UserDao
import org.ktorm.schema.Table
import org.ktorm.schema.long
import org.ktorm.schema.varchar

object UserTable : Table<UserDao>("users") {
    val cpf = varchar("cpf").primaryKey().bindTo { it.cpf }
    val name = varchar("name").bindTo { it.name }
    val createdAt = long("created_at").bindTo { it.createdAt }
}


