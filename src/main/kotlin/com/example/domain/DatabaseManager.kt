package com.example.domain

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import org.ktorm.database.Database

object DatabaseManager {
    private val appConfig = HoconApplicationConfig(ConfigFactory.load())
    private val driver = appConfig.property("database.driver").getString()
    private val url = appConfig.property("database.url").getString()

    val databaseConnection = Database.connect("$driver:$url")
}
