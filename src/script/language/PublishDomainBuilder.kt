package it.oechsler.script.language

import it.oechsler.script.data.Domain
import it.oechsler.script.data.PublishDomains

@Suppress("unused")
class PublishDomainBuilder private constructor(private val port: Int) {
    private var hostnamesToPublish: Set<String> = emptySet()

    companion object {

        fun domains(port: Int, init: PublishDomainBuilder.() -> Unit): PublishDomainBuilder {
            return PublishDomainBuilder(port).apply(init)
        }

    }

    fun domain(domain: String) {
        val mutableSet = this.hostnamesToPublish.toMutableSet()
        // validate hostname, because the whole obj is not needed in the JSON Payload
        mutableSet.add(Domain(domain).host)
        this.hostnamesToPublish = mutableSet.toSet()
    }
    
    fun toPublishDomains(): PublishDomains {
        return PublishDomains(this.port, this.hostnamesToPublish)
    }

}

