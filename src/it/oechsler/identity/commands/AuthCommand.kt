package it.oechsler.identity.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.trendyol.kediatr.CommandBus
import it.oechsler.identity.AuthCommandBus
import it.oechsler.identity.data.AuthConfig
import it.oechsler.identity.data.AuthToken
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

class AuthCommand : CliktCommand(name = "auth", help = "Authenticate with the cozy it.oechsler.api", printHelpOnEmptyArgs = true),
    KoinComponent {

    private val authCommandBus by inject<CommandBus>(named<AuthCommandBus>())

    private val token by option("-t", "--token", help = "Provide an access token")

    override fun run() {
        token?.let {
            return saveConfig(it)
        }

        // Other options might be possible from here,
        // for example OAuth with the it.oechsler.api in a web browser
    }

    private fun saveConfig(token: String) {
        val authToken = AuthToken(token)
        val authConfig = AuthConfig(authToken)
        val saveAuthConfigCommand = SaveAuthConfigCommand(authConfig)
        authCommandBus.executeCommand(saveAuthConfigCommand)

        echo("Successfully authenticated with token \"$authToken\".")
    }

}