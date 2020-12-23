package it.oechsler.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import it.oechsler.repositories.ConfigRepository
import it.oechsler.repositories.data.Config
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class AuthCommand: CliktCommand(name = "auth", help = "Authorize with Token"), KoinComponent {

    private val configRepository: ConfigRepository by inject()
    private val token by option("-t", "--token", help = "Bearer Token")

    override fun run() {
        token?.let { Config(it) }?.let { configRepository.create(it) }

    }

}