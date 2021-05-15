package it.oechsler.script.data

import kotlinx.serialization.Serializable
import org.valiktor.functions.isIn
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate

@Serializable
data class Port(
    val name: String? = null,
    val source: Int,
    val destination: Int
) {

    constructor(namedSourcePort: NameAndSourcePort, destination: Int): this(
        name = namedSourcePort.name,
        source = namedSourcePort.sourcePort,
        destination
    )

    init {
        validate(this) {
            validate(Port::name).isNotEmpty().isNotBlank()
            validate(Port::source).isIn(1..65535)
            validate(Port::destination).isIn(1..65535)
        }
    }

}
