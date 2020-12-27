package it.oechsler

import com.github.ajalt.clikt.core.subcommands
import it.oechsler.commands.ApplyCommand
import it.oechsler.commands.AuthCommand
import it.oechsler.commands.MainCommand
import it.oechsler.features.features
import it.oechsler.repositories.repositories
import it.oechsler.services.services
import org.koin.core.component.KoinApiExtension
import org.koin.core.context.startKoin

@KoinApiExtension
fun main(args: Array<String>) {
    startKoin {
        // Register modules
        // for dependency injection
        features()
        services()
        repositories()

        // Build the command line parser
        MainCommand()
            .subcommands(AuthCommand())
            .subcommands(ApplyCommand())
            .main(args)
    }
}