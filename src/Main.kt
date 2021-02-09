package it.oechsler

import com.github.ajalt.clikt.core.subcommands
import it.oechsler.apply.apply
import it.oechsler.apply.commands.ApplyCommand
import it.oechsler.identity.commands.AuthCommand
import it.oechsler.identity.identity
import it.oechsler.main.commands.MainCommand
import org.koin.core.component.KoinApiExtension
import org.koin.core.context.startKoin

@KoinApiExtension
fun main(args: Array<String>) {
    startKoin {
        // Register modules
        // for dependency injection
        identity()
        apply()

        // Build the command line parser
        MainCommand()
            .subcommands(AuthCommand())
            .subcommands(ApplyCommand())
            .main(args)
    }
}