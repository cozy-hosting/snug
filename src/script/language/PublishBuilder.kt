package it.oechsler.script.language

import it.oechsler.script.data.Publish
import it.oechsler.script.data.PublishDomains
import it.oechsler.script.data.PublishPort

@Suppress("unused")
class PublishBuilder private constructor() {

    companion object {

        fun publish(init: PublishBuilder.() -> Unit): PublishBuilder {
            return PublishBuilder().apply(init)
        }

    }

    private var publishDomains = setOf<PublishDomains>()
    private var publishPorts = setOf<PublishPort>()

    fun domains(port: Int, block: PublishDomainBuilder.() -> Unit) {
        val mutableSet = this.publishDomains.toMutableSet()
        mutableSet.add(PublishDomainBuilder.domains(port, block).toPublishDomains())
        this.publishDomains = mutableSet
    }

    fun port(port: Int) {
        val mutableSet = this.publishPorts.toMutableSet()
        mutableSet.add(PublishPortBuilder.port(port).toPublishPort())
        this.publishPorts = mutableSet
    }

    fun toPublish(): Publish {
        return Publish(publishPorts, publishDomains)
    }


}



