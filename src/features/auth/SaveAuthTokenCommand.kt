package it.oechsler.features.auth

import com.trendyol.kediatr.Command
import it.oechsler.features.auth.data.AuthToken
import org.valiktor.functions.isNotNull
import org.valiktor.validate

class SaveAuthTokenCommand(val authToken: AuthToken) : Command {

    init {
        validate(this) {
            validate(SaveAuthTokenCommand::authToken)
                .isNotNull()
        }
    }

}