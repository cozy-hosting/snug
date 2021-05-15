package it.oechsler.config.data

import it.oechsler.identity.data.AuthConfig
import kotlinx.serialization.Serializable

@Serializable
data class Config(var authConfig: AuthConfig?)