package it.oechsler.script.data

import org.valiktor.functions.isIn
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate

data class NamedContainerPort(
    val name: String,
    val container: Int
) {
    init {
        validate(this) {
            validate(NamedContainerPort::name).isNotEmpty().isNotBlank()
            validate(NamedContainerPort::container).isIn(1..65535)
        }
    }
}
