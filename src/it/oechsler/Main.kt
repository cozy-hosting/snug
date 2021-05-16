package it.oechsler

import com.github.ajalt.clikt.core.subcommands
import it.oechsler.identity.commands.AuthCommand
import it.oechsler.identity.identity
import it.oechsler.commands.MainCommand
import it.oechsler.script.apply
import it.oechsler.script.commands.ApplyCommand
import it.oechsler.script.commands.RollbackCommand
import org.koin.core.context.GlobalContext.startKoin

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
            .subcommands(RollbackCommand())
            .main(args)
    }
}