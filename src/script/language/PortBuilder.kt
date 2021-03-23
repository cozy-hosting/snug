package it.oechsler.script.language

import it.oechsler.script.data.NamedContainerPort
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

    fun port(container: Int, pod: Int, name: String? = null) {
        port(Port(name, container, pod))
    }

    fun port(containerAndPod: Int, name: String? = null) {
        port(Port(name, containerAndPod, containerAndPod))
    }

    infix fun String.with(value: Int): NamedContainerPort {
        return NamedContainerPort(this, value)
    }

    infix fun NamedContainerPort.to(value: Int) {
        port(Port(this, value))
    }

    infix fun Int.to(value: Int) {
        port(Port(container = this, pod = value))
    }

    fun toPorts(): Set<Port> {
        return this.ports
    }

}