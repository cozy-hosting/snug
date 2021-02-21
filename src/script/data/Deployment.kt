package it.oechsler.script.data

import kotlinx.serialization.Serializable
import org.valiktor.functions.isIn
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate

@Serializable
data class Deployment(
    val name: String,
    val tags: Set<String>,
    val replicas: Int
) {

    init {
        validate(this) {
            validate(Deployment::name).isNotEmpty().isNotBlank()
            validate(Deployment::replicas).isIn(1..10)
        }
    }

}