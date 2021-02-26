package it.oechsler.script.language

import it.oechsler.script.data.Container
import it.oechsler.script.data.Deployment
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.properties.Delegates

@Suppress("unused")
class DeploymentBuilder private constructor(val name: String) : ScriptRoot {

    companion object {

        fun deploy(name: String, init: DeploymentBuilder.() -> Unit): DeploymentBuilder {
            return DeploymentBuilder(name).apply(init)
        }

    }

    var replicas by Delegates.notNull<Int>()
    private var tags = setOf<String>()
    private var containers = setOf<Container>()

    fun tags(vararg tags: String) {
        this.tags = tags.toSet()
    }

    fun toDeployment(): Deployment {
        return Deployment(name, tags, replicas, containers)
    }

     fun container(name: String, block: ContainerBuilder.() -> Unit) {
        val mutableSet = this.containers.toMutableSet()
        mutableSet.add(ContainerBuilder.container(name, block).toContainer())
        this.containers = mutableSet
     }

    override fun apply() {
        val serializer = Json { prettyPrint = true }
        println(serializer.encodeToString(toDeployment()))
    }

    override fun rollback() {
        TODO("Not yet implemented")
    }

}