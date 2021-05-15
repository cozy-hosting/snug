package it.oechsler.script

import it.oechsler.script.services.ScriptService
import it.oechsler.script.services.ScriptServiceImpl
import org.koin.core.KoinApplication
import org.koin.dsl.module

fun KoinApplication.apply() {
    val script = module(createdAtStart = true) {
        single<ScriptService> { ScriptServiceImpl() }
    }
    modules(script)
}