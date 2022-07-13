package com.example.plugins

import com.example.domain.routes.user.addUser
import com.example.domain.routes.user.getUser
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/server") {
            call.respondText("Hello World!")
        }
        getUser()
        addUser()
    }
}
