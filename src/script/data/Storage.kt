package it.oechsler.script.data

import kotlinx.serialization.Serializable
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate

@Serializable
data class Storage(
    val name: String,
    val size: String,
    val storageClass: String
) {
    init {
        validate(this) {
            validate(Storage::name)
                .isNotEmpty()
                .isNotBlank()
            validate(Storage::storageClass)
                .isNotEmpty()
                .isNotBlank()
        }
    }
}
