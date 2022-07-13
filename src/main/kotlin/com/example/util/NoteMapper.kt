package com.example.util

import com.example.data.entity.NoteDao
import com.example.data.model.response.NoteResponse

fun NoteDao.mapToNoteResponse(): NoteResponse {
    return NoteResponse(id, userId, title, description, createdAt)
}

fun List<NoteDao>.mapToNotesResponse(): List<NoteResponse> {
   return map { it.mapToNoteResponse() }
}
