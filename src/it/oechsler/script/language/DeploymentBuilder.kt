package it.oechsler.script.language

import it.oechsler.script.data.Container
import it.oechsler.script.data.Deployment
import it.oechsler.script.data.Mount
import it.oechsler.script.data.Publish
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.properties.Delegates

@Suppress("unused")
class DeploymentBuilder private constructor(val name: String) : Script {

    companion object {

        fun deployment(name: String, init: DeploymentBuilder.() -> Unit): DeploymentBuilder {
            return DeploymentBuilder(name).apply(init)
        }

    }

    var replicas by Delegates.notNull<Int>()
    private var tags = setOf<String>()
    private var containers = setOf<Container>()
    private var mounts = setOf<Mount>()
    private lateinit var publish: Publish

    fun tags(vararg tags: String) {
        this.tags = tags.toSet()
    }


    fun container(name: String, block: ContainerBuilder.() -> Unit) {
        val mutableSet = this.containers.toMutableSet()
        mutableSet.add(ContainerBuilder.container(name, block).toContainer())
        this.containers = mutableSet
    }

    fun mounts(block: MountBuilder.() -> Unit) {
        mounts = MountBuilder.mounts(block).toMounts()
    }

    fun publish(block: PublishBuilder.() -> Unit) {
        publish = PublishBuilder.publish(block).toPublish()
    }

    fun toDeployment(): Deployment {
        return Deployment(name, tags, replicas, containers, mounts, publish)
    }

    override fun apply() {
        val serializer = Json { prettyPrint = true }
        println(serializer.encodeToString(toDeployment()))
    }

    override fun rollback() {
        TODO("Not yet implemented")
    }
}
