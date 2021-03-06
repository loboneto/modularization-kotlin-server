package com.example.di

import com.example.data.source.NoteDataSource
import com.example.data.source.NoteDataSourceImpl
import com.example.data.source.UserDataSource
import com.example.data.source.UserDataSourceImpl
import com.example.domain.DatabaseManager
import org.koin.dsl.module

val dataSourceModule = module {

    val database = DatabaseManager.databaseConnection

    single<UserDataSource> { UserDataSourceImpl(database) }
    single<NoteDataSource> { NoteDataSourceImpl(database) }

}
