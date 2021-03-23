package it.oechsler.script.data

import kotlinx.serialization.Serializable
import org.valiktor.functions.isIn
import org.valiktor.validate

@Serializable
data class PublishDomains(
    var port: Int,
    var domains: Set<Domain>
) {
    init {
        validate(this) {
            validate(PublishDomains::port).isIn(1..65535)
        }
    }
}
