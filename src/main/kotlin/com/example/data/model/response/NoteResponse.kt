package com.example.data.model.response

data class NoteResponse(
    var id: Int,
    var userCpf: String,
    var title: String,
    var description: String,
    var createdAt: Long
)