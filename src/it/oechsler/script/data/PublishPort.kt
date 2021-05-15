package it.oechsler.script.data

import kotlinx.serialization.Serializable
import org.valiktor.functions.isIn
import org.valiktor.validate

@Serializable
data class PublishPort(
    var port: Int
) {
    init {
        validate(this) {
            validate(PublishPort::port).isIn(1..65535)
        }
    }
}
