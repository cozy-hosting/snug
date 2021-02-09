package it.oechsler.identity.commands

import com.trendyol.kediatr.CommandHandler
import it.oechsler.config.data.Config
import it.oechsler.config.repositories.ConfigRepository
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
@Suppress("unused")
class SaveAuthConfigCommandHandler : CommandHandler<SaveAuthConfigCommand>, KoinComponent {

    private val configRepository: ConfigRepository by inject()

    override fun handle(command: SaveAuthConfigCommand) {
        var currentConfig = configRepository.retrieve()

        if (currentConfig != null) {
            currentConfig.authConfig = command.authConfig
        } else {
            currentConfig = Config(command.authConfig)
        }

        configRepository.create(currentConfig)
    }

}