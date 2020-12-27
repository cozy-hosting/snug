package it.oechsler.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.trendyol.kediatr.CommandBus
import it.oechsler.features.AuthCommandBus
import it.oechsler.features.auth.SaveAuthTokenCommand
import it.oechsler.features.auth.data.AuthToken
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

@KoinApiExtension
class AuthCommand : CliktCommand(name = "auth", help = "Authenticate with the cozy api", printHelpOnEmptyArgs = true),
    KoinComponent {

    private val authCommandBus by inject<CommandBus>(named<AuthCommandBus>())

    private val token by option("-t", "--token", help = "Provide an access token")

    override fun run() {
        token?.let {
            return saveToken(it)
        }

        // Other options might be possible from here,
        // for example OAuth with the api in a web browser
    }

    private fun saveToken(token: String) {
        val authToken = AuthToken(token)

        val saveAuthTokenCommand = SaveAuthTokenCommand(authToken)
        authCommandBus.executeCommand(saveAuthTokenCommand)

        echo("Successfully authenticated with token \"$authToken\".")
    }

}