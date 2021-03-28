package it.oechsler.script.language

import it.oechsler.script.data.Container
import it.oechsler.script.data.Deployment
import it.oechsler.script.data.Publish
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.properties.Delegates

@Suppress("unused")
class DeploymentBuilder private constructor(val name: String) : ScriptRoot {

    companion object {

        fun deployment(name: String, init: DeploymentBuilder.() -> Unit): DeploymentBuilder {
            return DeploymentBuilder(name).apply(init)
        }

    }

    var replicas by Delegates.notNull<Int>()
    private var tags = setOf<String>()
    private var containers = setOf<Container>()
    private lateinit var publish: Publish

    fun tags(vararg tags: String) {
        this.tags = tags.toSet()
    }


     fun container(name: String, block: ContainerBuilder.() -> Unit) {
        val mutableSet = this.containers.toMutableSet()
        mutableSet.add(ContainerBuilder.container(name, block).toContainer())
        this.containers = mutableSet
     }

    fun publish(block: PublishBuilder.() -> Unit) {
        //publish = Publish(setOf(PublishPort(8080)))
        publish = PublishBuilder.publish(block).toPublish()
    }

    override fun apply() {
        val serializer = Json { prettyPrint = true }
        println(serializer.encodeToString(toDeployment()))
    }

    override fun rollback() {
        TODO("Not yet implemented")
    }

    fun toDeployment(): Deployment {
        return Deployment(name, tags, replicas, containers, publish)
    }
}
