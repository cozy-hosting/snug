package it.oechsler.script.language

import it.oechsler.script.data.NamedSourcePort
import it.oechsler.script.data.Port

@Suppress("unused")
class PortBuilder private constructor() {

    companion object {

        fun ports(init: PortBuilder.() -> Unit): PortBuilder {
            return PortBuilder().apply(init)
        }

    }

    private var ports = setOf<Port>()

    private fun port(port: Port) {
        val mutableSet = this.ports.toMutableSet()
        mutableSet.add(port)
        this.ports = mutableSet.toSet()
    }

    fun port(source: Int, destination: Int, name: String? = null) {
        port(Port(name, source, destination))
    }

    fun port(sourceAndDestination: Int, name: String? = null) {
        port(Port(name, sourceAndDestination, sourceAndDestination))
    }

    infix fun String.with(value: Int): NamedSourcePort {
        return NamedSourcePort(this, value)
    }

    infix fun NamedSourcePort.to(value: Int) {
        port(Port(this, value))
    }

    infix fun Int.to(value: Int) {
        port(Port(source = this, destination = value))
    }

    fun toPorts(): Set<Port> {
        return this.ports
    }

}