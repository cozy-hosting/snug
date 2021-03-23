package it.oechsler.script.language

import it.oechsler.script.data.LoadBalancedDeployment
import it.oechsler.script.data.LoadBalancer

class LoadBalancerBuilder private constructor(val name: String) {

    private var loadBalancedDeployments = setOf<LoadBalancedDeployment>()

    companion object {

        fun loadBalancer(name: String, init: LoadBalancerBuilder.() -> Unit): LoadBalancerBuilder {
            return LoadBalancerBuilder(name).apply(init)
        }

    }

    fun deployment(name: String, block: LoadBalancedDeploymentBuilder.() -> Unit) {
        val mutableSet = this.loadBalancedDeployments.toMutableSet()
        mutableSet.add(LoadBalancedDeploymentBuilder.deployment(name, block).toLoadBalancedDeployment())
        this.loadBalancedDeployments = mutableSet
    }

    fun toLoadBalancer(): LoadBalancer {
        return LoadBalancer(name, loadBalancedDeployments)
    }

}
