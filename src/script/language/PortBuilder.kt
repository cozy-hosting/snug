package it.oechsler.script.language

import it.oechsler.script.data.Port

@Suppress("unused")
class PortBuilder private constructor() {

    companion object {

        fun portsBlock(init: PortBuilder.() -> Unit): PortBuilder {
            return PortBuilder().apply(init)
        }

    }

    private var ports = setOf<Port>()

    infix fun String.bind (value: String) {
        val ports = value.split(":")
        addPort(Port(this, ports[0].toInt(), ports[1].toInt()))
    }

    fun addPort(port: Port){
        val mutableSet = this.ports.toMutableSet()
        mutableSet.add(port)
        this.ports = mutableSet
    }

    fun toPorts(): Set<Port> {
        return this.ports
    }

}

