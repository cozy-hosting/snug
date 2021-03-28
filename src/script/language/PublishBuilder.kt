package it.oechsler.script.language

import it.oechsler.script.data.Publish
import it.oechsler.script.data.PublishDomains

@Suppress("unused")
class PublishBuilder private constructor() {

    private var publishDomains = setOf<PublishDomains>()
    private var publishPorts = setOf<Int>()

    companion object {

        fun publish(init: PublishBuilder.() -> Unit): PublishBuilder {
            return PublishBuilder().apply(init)
        }

    }

    fun domains(port: Int, block: PublishDomainBuilder.() -> Unit) {
        val mutableSet = this.publishDomains.toMutableSet()
        mutableSet.add(PublishDomainBuilder.domains(port, block).toPublishDomains())
        this.publishDomains = mutableSet
    }

    fun port(port: Int) {
        val mutableSet = this.publishPorts.toMutableSet()
        // create the PublishPort obj to validate port is and valid input
        PublishPortBuilder.port(port).toPublishPort()
        mutableSet.add(port)
        this.publishPorts = mutableSet
    }

    fun toPublish(): Publish {
        return Publish(publishPorts, publishDomains)
    }


}



