package com.example.data.entity

import org.ktorm.entity.Entity

interface NoteDao : Entity<NoteDao> {

    companion object : Entity.Factory<NoteDao>()

    val id: Int
    val userCpf: String
    val title: String
    val description: String
    val createdAt: Long

}
