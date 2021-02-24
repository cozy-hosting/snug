package it.oechsler.script.data

import kotlinx.serialization.Serializable
import org.valiktor.functions.isIn
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate

@Serializable
data class Port(
    val name: String,
    val container: Int,
    val pod: Int
){
    init {
        validate(this) {
            validate(Port::name).isNotEmpty().isNotBlank()
            validate(Port::container).isIn(1..65535)
            validate(Port::pod).isIn(1..65535)
        }
    }
}
