package com.example.data.database

import com.example.data.entity.NoteDao
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.long
import org.ktorm.schema.varchar

object NoteTable : Table<NoteDao>("notes") {
    val id = int("id").primaryKey().bindTo { it.id }
    val userCpf = varchar("userCpf").bindTo { it.userCpf }
    val title = varchar("title").bindTo { it.title }
    val description = varchar("v").bindTo { it.description }
    val createdAt = long("created_at").bindTo { it.createdAt }
}


