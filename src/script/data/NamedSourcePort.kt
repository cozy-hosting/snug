package it.oechsler.script.data

import org.valiktor.functions.isIn
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate

data class NamedSourcePort(
    val name: String,
    val source: Int
) {
    init {
        validate(this) {
            validate(NamedSourcePort::name).isNotEmpty().isNotBlank()
            validate(NamedSourcePort::source).isIn(1..65535)
        }
    }
}
