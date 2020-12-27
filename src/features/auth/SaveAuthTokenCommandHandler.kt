package it.oechsler.features.auth

import com.trendyol.kediatr.CommandHandler
import it.oechsler.repositories.ConfigRepository
import it.oechsler.repositories.data.Config
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
@Suppress("unused")
class SaveAuthTokenCommandHandler : CommandHandler<SaveAuthTokenCommand>, KoinComponent {

    private val configRepository: ConfigRepository by inject()

    override fun handle(command: SaveAuthTokenCommand) {
        // TODO: Add proper mutation of config once needed
        // val currentConfig = configRepository.retrieve()

        val mutatedConfig = Config(command.authToken.toString())

        configRepository.create(mutatedConfig)
    }

}