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

    //TODO: Provide list of common ports to name the port-mapping
    infix fun Int.to (value: Int) {
        addPort(Port(this, value))
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

