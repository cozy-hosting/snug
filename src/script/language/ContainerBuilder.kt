package it.oechsler.script.language

import it.oechsler.script.data.Container
import it.oechsler.script.data.Image
import it.oechsler.script.data.Port

@Suppress("unused")
class ContainerBuilder private constructor(val name: String) {

    companion object {

        fun container(name: String, init: ContainerBuilder.() -> Unit): ContainerBuilder {
            return ContainerBuilder(name).apply(init)
        }

    }

    lateinit var image: Image
    private var ports = setOf<Port>()

    fun ports(block: PortBuilder.() -> Unit) {
        this.ports = PortBuilder.ports(block).toPorts()
    }

    fun toContainer(): Container {
        return Container(name, image, ports)
    }


}

