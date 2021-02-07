package it.oechsler.identity.data

import kotlinx.serialization.Serializable
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate

@Serializable
data class AuthToken(val token: String) {

    init {
        validate(this) {
            validate(AuthToken::token)
                .isNotEmpty()
                .isNotBlank()
        }
    }

    override fun toString(): String {
        return token
    }

}

