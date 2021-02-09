package it.oechsler.identity.data

import kotlinx.serialization.Serializable


@Serializable
data class AuthConfig(val authToken: AuthToken?)