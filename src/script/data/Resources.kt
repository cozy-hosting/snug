package it.oechsler.script.data

import kotlinx.serialization.Serializable
import org.valiktor.validate

@Serializable
data class Resources(
    val deployments: Set<Deployment>,
    val loadBalancers: Set<LoadBalancer>,
) {
    init {
        validate(this) {

        }
    }
}