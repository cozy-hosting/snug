package it.oechsler.script.data

import org.valiktor.functions.isIn
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate

data class NameAndSourcePort(
    val name: String,
    val sourcePort: Int
) {
    init {
        validate(this) {
            validate(NameAndSourcePort::name).isNotEmpty().isNotBlank()
            validate(NameAndSourcePort::sourcePort).isIn(1..65535)
        }
    }
}
