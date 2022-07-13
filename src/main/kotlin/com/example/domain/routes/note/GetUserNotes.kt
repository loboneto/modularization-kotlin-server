package com.example.domain.routes.note

import com.example.data.source.NoteDataSource
import com.example.util.mapToNotesResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.getUserNotes() {

    val notesDataSource by inject<NoteDataSource>()

    get("notes/getByUser/{userCpf}") {
        val userCpf = call.parameters["userCpf"] ?: run {
            call.respond(HttpStatusCode.BadRequest, "CPF do usuário não informado!")
            return@get
        }

        val notes = try {
            notesDataSource.getUserNotes(userCpf).mapToNotesResponse()
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, "Erro ao obter lista de notas!")
            return@get
        }

        call.respond(HttpStatusCode.OK, notes)
    }
}
