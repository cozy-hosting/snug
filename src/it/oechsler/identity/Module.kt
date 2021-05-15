package it.oechsler.identity

import com.trendyol.kediatr.CommandBusBuilder
import it.oechsler.config.repositories.ConfigRepository
import it.oechsler.config.repositories.ConfigRepositoryImpl
import it.oechsler.identity.commands.SaveAuthConfigCommand
import org.koin.core.KoinApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

enum class AuthCommandBus

fun KoinApplication.identity() {
    val identity = module(createdAtStart = true) {
        single(named<AuthCommandBus>()) { CommandBusBuilder(SaveAuthConfigCommand::class.java).build() }
        single<ConfigRepository> { ConfigRepositoryImpl() }
    }
    modules(identity)
}