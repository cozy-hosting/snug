package it.oechsler.script.language

import it.oechsler.script.data.Domain
import it.oechsler.script.data.PublishDomains

@Suppress("unused")
class PublishDomainBuilder private constructor(private val port: Int) {
    private var domainsToPublish: Set<Domain> = emptySet()

    companion object {

        fun domains(port: Int, init: PublishDomainBuilder.() -> Unit): PublishDomainBuilder {
            return PublishDomainBuilder(port).apply(init)
        }

    }

    fun domain(domain: String) {
        val mutableSet = this.domainsToPublish.toMutableSet()
        mutableSet.add(Domain(domain))
        this.domainsToPublish = mutableSet.toSet()
    }


    fun toPublishDomains(): PublishDomains {

        return PublishDomains(this.port, this.domainsToPublish)
    }

}

