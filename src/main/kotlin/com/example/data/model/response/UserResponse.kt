package com.example.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    var cpf: String,
    var name: String,
    var createdAt: Long
)
