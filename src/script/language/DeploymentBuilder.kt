package it.oechsler.script.language

import it.oechsler.script.data.Deployment
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.properties.Delegates

@Suppress("unused")
class DeploymentBuilder private constructor() : ScriptRoot {

    companion object {

        fun deploy(init: DeploymentBuilder.() -> Unit): DeploymentBuilder {
            return DeploymentBuilder().apply(init);
        }

    }

    lateinit var name: String
    var replicas by Delegates.notNull<Int>()
    private var tags = setOf<String>()

    fun withTags(vararg tags: String) {
        this.tags = tags.toSet()
    }

    fun toDeployment(): Deployment {
        return Deployment(name, tags, replicas);
    }

    // TODO: Do this for containers ...
    // fun withDeployment(block: DeploymentBuilder.() -> Unit) {
    //     deploy(block).toDeployment()
    // }

    override fun apply() {
        val serializer = Json { prettyPrint = true }
        println(serializer.encodeToString(toDeployment()))
    }

    override fun rollback() {
        TODO("Not yet implemented")
    }

}

