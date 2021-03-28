package it.oechsler.script.data

import kotlinx.serialization.Serializable
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate

@Serializable
data class LoadBalancedDeployment(
    val name: String,
    val ports: Set<Port>
) {
    init {
        validate(this) {
            validate(LoadBalancedDeployment::name).isNotEmpty().isNotBlank()
        }
    }
}