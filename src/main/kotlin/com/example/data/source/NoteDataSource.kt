package com.example.data.source

import com.example.data.database.NoteTable
import com.example.data.database.UserTable
import com.example.data.entity.NoteDao
import com.example.data.model.request.NoteRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.ktorm.database.Database
import org.ktorm.dsl.*
import java.util.*

interface NoteDataSource {
    suspend fun getUserNotes(userCpf: String): List<NoteDao>
    suspend fun getNoteById(noteId: Int): NoteDao?
    suspend fun addNote(noteRequest: NoteRequest): Int
    suspend fun updateNote(noteId: Int, noteRequest: NoteRequest): Boolean
    suspend fun deleteNote(noteId: Int): Boolean
}

class NoteDataSourceImpl(private val database: Database) : NoteDataSource {

    override suspend fun getUserNotes(userCpf: String): List<NoteDao> = withContext(Dispatchers.IO) {
        return@withContext database.from(UserTable)
            .select(NoteTable.columns)
            .where { NoteTable.userCpf eq userCpf }
            .map { NoteTable.createEntity(it) }
    }

    override suspend fun getNoteById(noteId: Int): NoteDao? = withContext(Dispatchers.IO) {
        return@withContext database.from(NoteTable)
            .select(NoteTable.columns)
            .where { NoteTable.id eq noteId }
            .map { NoteTable.createEntity(it) }
            .firstOrNull()
    }

    override suspend fun addNote(noteRequest: NoteRequest): Int =
        withContext(Dispatchers.IO) {
            database.insertAndGenerateKey(NoteTable) {
                set(NoteTable.userCpf, noteRequest.userCpf)
                set(NoteTable.title, noteRequest.title)
                set(NoteTable.description, noteRequest.description)
                set(NoteTable.createdAt, Calendar.getInstance(Locale("pt", "BR")).timeInMillis)
            } as Int
        }

    override suspend fun updateNote(noteId: Int, noteRequest: NoteRequest): Boolean = withContext(Dispatchers.IO) {
        return@withContext database.update(NoteTable) {
            set(NoteTable.userCpf, noteRequest.userCpf)
            set(NoteTable.title, noteRequest.title)
            set(NoteTable.description, noteRequest.description)
            where { it.id eq noteId }
        } > 0
    }

    override suspend fun deleteNote(noteId: Int): Boolean = withContext(Dispatchers.IO) {
        return@withContext database.delete(NoteTable) { it.id eq noteId } > 0
    }
}
