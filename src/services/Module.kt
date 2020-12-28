package it.oechsler.services

import io.ktor.client.*
import org.koin.core.KoinApplication
import org.koin.dsl.module

fun KoinApplication.services() {
    val services = module(createdAtStart = true) {
        single<ScriptService> { ScriptServiceImpl() }
        single<ApiService> { ApiServiceImpl() }

        single { HttpClient() }
    }
    modules(services)
}