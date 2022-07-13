package com.example.util

import com.example.data.entity.UserDao
import com.example.data.model.response.UserResponse

fun UserDao.mapToUserResponse(): UserResponse {
    return UserResponse(cpf, name, createdAt)
}
