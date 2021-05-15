package it.oechsler.script.data

import kotlinx.serialization.Serializable
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotEmpty
import org.valiktor.functions.matches
import org.valiktor.validate

@Serializable
data class Domain(
    val host: String
) {
    init {
        validate(this) {
            validate(Domain::host).isNotEmpty().isNotBlank().matches(Regex("^([a-z0-9]+(-[a-z0-9]+)*\\.)+[a-z]{2,}\$"))
        }
    }
}
