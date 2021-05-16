package it.oechsler.script.language

import it.oechsler.script.SnugDsl
import it.oechsler.script.data.LoadBalancedDeployment
import it.oechsler.script.data.Port

@SnugDsl
class LoadBalancedDeploymentBuilder private constructor(val name: String) {

    private var ports = setOf<Port>()

    companion object {

        fun deployment(name: String, block: LoadBalancedDeploymentBuilder.() -> Unit): LoadBalancedDeploymentBuilder {
            return LoadBalancedDeploymentBuilder(name).apply(block)
        }

    }

    fun ports(block: PortBuilder.() -> Unit) {
        this.ports = PortBuilder.ports(block).toPorts()
    }

    fun toLoadBalancedDeployment(): LoadBalancedDeployment {
        return LoadBalancedDeployment(name, ports)
    }

}
