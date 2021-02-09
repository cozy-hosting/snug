package it.oechsler.services

import io.ktor.client.*
import it.oechsler.api.services.ApiService
import it.oechsler.api.services.ApiServiceImpl
import org.koin.core.KoinApplication
import org.koin.dsl.module

fun KoinApplication.services() {
    val services = module(createdAtStart = true) {
        single<ApiService> { ApiServiceImpl() }
        single { HttpClient() }
    }
    modules(services)
}