package it.oechsler.script.data

import kotlinx.serialization.Serializable
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate

@Serializable
data class Mount(
    val name: String,
    val type: String,
    val path: String,
    val permission: String
) {
    init {
        validate(this) {
            validate(Mount::name)
                .isNotEmpty()
                .isNotBlank()
        }
    }
}