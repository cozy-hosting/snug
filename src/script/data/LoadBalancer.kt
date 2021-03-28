package it.oechsler.script.data

import kotlinx.serialization.Serializable
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate

@Serializable
data class LoadBalancer(
    val name: String,
    val deployment: Set<LoadBalancedDeployment>,
) {
    init {
        validate(this) {
            validate(LoadBalancer::name).isNotEmpty().isNotBlank()
        }
    }
}