package it.oechsler.repositories

import org.koin.core.KoinApplication
import org.koin.dsl.module

fun KoinApplication.repositories() {
    val repositories = module(createdAtStart = true) {
        single<ConfigRepository> { ConfigRepositoryImpl() }
    }
    modules(repositories)
}