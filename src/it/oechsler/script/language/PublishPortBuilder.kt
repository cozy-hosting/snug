package it.oechsler.script.language

import it.oechsler.script.data.PublishPort

class PublishPortBuilder private constructor(private val port: Int) {

    companion object {

        fun port(port: Int) : PublishPortBuilder {
            return PublishPortBuilder(port)
        }

    }

    fun toPublishPort(): PublishPort {
        return PublishPort(port)
    }

}