package com.example.routes.user

import com.example.data.source.UserDataSource
import com.example.util.mapToUserResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.getUser() {

    val userDataSource by inject<UserDataSource>()
    get("users/{userCpf}") {

        val userCpf = call.parameters["userCpf"] ?: run {
            call.respond(HttpStatusCode.BadRequest, "CPF do usuário não informado!")
            return@get
        }
        val user = try {
            userDataSource.getUserByCpf(userCpf)!!
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, "Erro ao adicionar usuário")
            return@get
        }
        call.respond(HttpStatusCode.OK, user.mapToUserResponse())
    }
}
