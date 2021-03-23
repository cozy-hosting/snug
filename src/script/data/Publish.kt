package it.oechsler.script.data

import kotlinx.serialization.Serializable

@Serializable
data class Publish(
    var ports: Set<PublishPort>? = null,
    var domains: Set<PublishDomains>? = null
)
