package it.oechsler.identity.commands

import com.trendyol.kediatr.Command
import it.oechsler.identity.data.AuthConfig
import org.valiktor.functions.isNotNull
import org.valiktor.validate

class SaveAuthConfigCommand(val authConfig: AuthConfig) : Command {

    init {
        validate(this) {
            validate(SaveAuthConfigCommand::authConfig)
                .isNotNull()
        }
    }

}