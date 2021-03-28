package it.oechsler.script.data

import kotlinx.serialization.Serializable

@Serializable
data class Publish(
    var ports: Set<Int>? = null,
    var domains: Set<PublishDomains>? = null
)
