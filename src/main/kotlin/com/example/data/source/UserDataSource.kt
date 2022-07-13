package com.example.data.source

import com.example.data.database.UserTable
import com.example.data.entity.UserDao
import com.example.data.model.request.UserRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.ktorm.database.Database
import org.ktorm.dsl.*
import java.util.*

interface UserDataSource {
    suspend fun getUsers(): List<UserDao>
    suspend fun getUserByCpf(cpf: String): UserDao?
    suspend fun addUser(userRequest: UserRequest): Boolean
    suspend fun updateUser(userCpf: String, userRequest: UserRequest): Boolean
    suspend fun deleteUser(userCpf: String): Boolean
}

class UserDataSourceImpl(private val database: Database) : UserDataSource {

    override suspend fun getUsers(): List<UserDao> = withContext(Dispatchers.IO) {
        return@withContext database.from(UserTable)
            .select(UserTable.columns)
            .map { UserTable.createEntity(it) }
    }

    override suspend fun getUserByCpf(cpf: String): UserDao? = withContext(Dispatchers.IO) {
        return@withContext database.from(UserTable)
            .select(UserTable.columns)
            .where { UserTable.cpf eq cpf }
            .map { UserTable.createEntity(it) }
            .firstOrNull()
    }

    override suspend fun addUser(userRequest: UserRequest): Boolean =
        withContext(Dispatchers.IO) {
            database.insert(UserTable) {
                set(UserTable.cpf, userRequest.cpf)
                set(UserTable.name, userRequest.name)
                set(UserTable.createdAt, Calendar.getInstance(Locale("pt", "BR")).timeInMillis)
            } > 0
        }

    override suspend fun updateUser(userCpf: String, userRequest: UserRequest): Boolean = withContext(Dispatchers.IO) {
        return@withContext database.update(UserTable) {
            set(UserTable.cpf, userRequest.cpf)
            set(UserTable.name, userRequest.name)
            where { it.cpf eq userCpf }
        } > 0
    }

    override suspend fun deleteUser(userCpf: String): Boolean = withContext(Dispatchers.IO) {
        return@withContext database.delete(UserTable) { it.cpf eq userCpf } > 0
    }
}
