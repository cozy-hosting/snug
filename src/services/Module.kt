package it.oechsler.services

import org.koin.core.KoinApplication
import org.koin.dsl.module

fun KoinApplication.services() {
    val services = module(createdAtStart = true) {
        single<ScriptService> { ScriptServiceImpl() }
    }
    modules(services)
}