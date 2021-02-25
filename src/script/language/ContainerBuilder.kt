package it.oechsler.script.language

import it.oechsler.script.data.Container
import it.oechsler.script.data.Image
import it.oechsler.script.data.Port

@Suppress("unused")
class ContainerBuilder private constructor() {

    companion object {

        fun container(init: ContainerBuilder.() -> Unit): ContainerBuilder {
            return ContainerBuilder().apply(init)
        }

    }

    lateinit var name: String
    lateinit var image: Image
    private var ports = setOf<Port>()

    fun ports(block: PortBuilder.() -> Unit) {
        this.ports = PortBuilder.ports(block).toPorts()
    }

    fun toContainer(): Container {
        return Container(name, image, ports)
    }


}

