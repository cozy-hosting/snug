package it.oechsler

import com.github.ajalt.clikt.core.subcommands
import it.oechsler.commands.ApplyCommand
import it.oechsler.commands.HelloWorldCommand
import it.oechsler.commands.MainCommand
import it.oechsler.services.services
import org.koin.core.context.startKoin

fun main(args: Array<String>) {
    startKoin {
        services()

        MainCommand()
            .subcommands(HelloWorldCommand(), ApplyCommand())
            .main(args)
    }
}