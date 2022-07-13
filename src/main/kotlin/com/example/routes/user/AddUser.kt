package com.example.routes

import com.example.data.model.request.UserRequest
import com.example.data.source.UserDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.addUser() {

    val userDataSource by inject<UserDataSource>()
    post("users") {

        val userRequest = try {
            call.receive<UserRequest>()
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, "Usuário com formato inválido!")
            return@post
        }
        val user = try {
            userDataSource.addUser(userRequest)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, "Erro ao adicionar usuário")
            return@post
        }
        call.respond(HttpStatusCode.OK, "Usuário adicionado com sucesso!")
    }
}