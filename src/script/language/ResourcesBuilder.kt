package it.oechsler.script.language

@Suppress("unused")
class ResourcesBuilder private constructor() : ScriptRoot {

    companion object {

        fun resources(init: ResourcesBuilder.() -> Unit): ResourcesBuilder {
            return ResourcesBuilder().apply(init)
        }

    }

    private var resourceGroup = setOf<ScriptRoot>()

    fun deployment(name: String, block: DeploymentBuilder.() -> Unit) {
        this.addScriptRoot(DeploymentBuilder.deployment(name, block))
    }

    fun loadBalancer(name: String, block: LoadBalancerBuilder.() -> Unit) {
        this.addScriptRoot(LoadBalancerBuilder.loadBalancer(name, block))
    }

    private fun addScriptRoot(scriptRoot: ScriptRoot) {
        val mutableSet = this.resourceGroup.toMutableSet()
        mutableSet.add(scriptRoot)
        this.resourceGroup = mutableSet
    }

    override fun apply() {
        resourceGroup.forEach{
            it.apply()
        }
    }

    override fun rollback() {
        TODO("Not yet implemented")
    }

}