package it.oechsler.script.data

import kotlinx.serialization.Serializable
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate

@Serializable
data class Container(
    val name: String,
    val image: Image,
    val ports: Set<Port>,
) {
    init {
        validate(this) {
            validate(Container::name).isNotEmpty().isNotBlank()
        }
    }
}
