package it.oechsler.script.data

import kotlinx.serialization.Serializable

@Serializable
data class Resources(
    val deployments: Set<Deployment>,
    val loadBalancers: Set<LoadBalancer>,
)