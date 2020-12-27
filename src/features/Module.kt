package it.oechsler.features

import com.trendyol.kediatr.CommandBusBuilder
import it.oechsler.features.auth.SaveAuthTokenCommand
import org.koin.core.KoinApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

enum class AuthCommandBus

fun KoinApplication.features() {
    val features = module(createdAtStart = true) {
        single(named<AuthCommandBus>()) { CommandBusBuilder(SaveAuthTokenCommand::class.java).build() }
    }
    modules(features)
}