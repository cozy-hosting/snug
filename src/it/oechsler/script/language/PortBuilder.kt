package it.oechsler.script.language

import it.oechsler.script.SnugDsl
import it.oechsler.script.data.NameAndSourcePort
import it.oechsler.script.data.Port

@SnugDsl
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

    // Syntax for binding a port with name
    infix fun String.from(sourcePort: Int): NameAndSourcePort {
        return NameAndSourcePort(this, sourcePort)
    }

    infix fun NameAndSourcePort.to(destinationPort: Int) {
        port(Port(this, destinationPort))
    }
    // END: Syntax for binding a port with name

    // Syntax for binding a port without a name
    infix fun Int.to(destinationPort: Int) {
        port(Port(source = this, destination = destinationPort))
    }
    // END: Syntax for binding a port without a name

    fun toPorts(): Set<Port> {
        return this.ports
    }

}