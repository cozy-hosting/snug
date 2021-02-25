package it.oechsler.script.data

import kotlinx.serialization.Serializable
import org.valiktor.functions.isIn
import org.valiktor.validate

@Serializable
data class Port(
    val container: Int,
    val pod: Int
){
    init {
        validate(this) {
            validate(Port::container).isIn(1..65535)
            validate(Port::pod).isIn(1..65535)
        }
    }
}
