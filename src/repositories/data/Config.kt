package it.oechsler.repositories.data

import kotlinx.serialization.Serializable

@Serializable
data class Config(val token: String) {
}