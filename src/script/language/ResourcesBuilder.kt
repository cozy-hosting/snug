package it.oechsler.script.language

import it.oechsler.script.data.Deployment
import it.oechsler.script.data.LoadBalancer
import it.oechsler.script.data.Resources
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Suppress("unused")
class ResourcesBuilder private constructor() : ScriptRoot {

    companion object {

        fun resources(init: ResourcesBuilder.() -> Unit): ResourcesBuilder {
            return ResourcesBuilder().apply(init)
        }

    }

    private var deployments = setOf<Deployment>()
    private var loadBalancers = setOf<LoadBalancer>()

    fun toResources(): Resources {
        return Resources(deployments, loadBalancers)
    }

     fun deployment(name: String, block: DeploymentBuilder.() -> Unit) {
        val mutableSet = this.deployments.toMutableSet()
        mutableSet.add(DeploymentBuilder.deployment(name, block).toDeployment())
        this.deployments= mutableSet
     }

    fun loadBalancer(name: String, block: LoadBalancerBuilder.() -> Unit) {
        val mutableSet = this.loadBalancers.toMutableSet()
        mutableSet.add(LoadBalancerBuilder.loadBalancer(name, block).toLoadBalancer())
        this.loadBalancers = mutableSet
    }

    override fun apply() {
        val serializer = Json { prettyPrint = true }
        println(serializer.encodeToString(toResources()))
    }

    override fun rollback() {
        TODO("Not yet implemented")
    }

}