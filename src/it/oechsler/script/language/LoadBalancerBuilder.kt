package it.oechsler.script.language

import it.oechsler.script.data.LoadBalancedDeployment
import it.oechsler.script.data.LoadBalancer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Suppress("unused")
class LoadBalancerBuilder private constructor(val name: String): Script {

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

    override fun apply() {
        val serializer = Json { prettyPrint = true }
        println(serializer.encodeToString(toLoadBalancer()))
    }

    override fun rollback() {
        TODO("Not yet implemented")
    }

}
