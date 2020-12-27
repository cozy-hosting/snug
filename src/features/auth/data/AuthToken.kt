package it.oechsler.features.auth.data

import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate

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

