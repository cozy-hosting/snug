package it.oechsler.apply

import it.oechsler.apply.services.ScriptService
import it.oechsler.apply.services.ScriptServiceImpl
import org.koin.core.KoinApplication
import org.koin.dsl.module

fun KoinApplication.apply() {
    val apply = module(createdAtStart = true) {
        single<ScriptService> { ScriptServiceImpl() }
    }
    modules(apply)
}