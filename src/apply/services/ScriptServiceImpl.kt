package it.oechsler.apply.services

import it.oechsler.services.data.ScriptException
import it.oechsler.services.data.SnugScript
import java.nio.file.Files
import java.nio.file.Path
import kotlin.reflect.KClass
import kotlin.reflect.jvm.jvmName
import kotlin.script.experimental.api.ResultValue
import kotlin.script.experimental.api.valueOrNull
import kotlin.script.experimental.host.toScriptSource
import kotlin.script.experimental.jvm.dependenciesFromCurrentContext
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost
import kotlin.script.experimental.jvmhost.createJvmCompilationConfigurationFromTemplate

class ScriptServiceImpl : ScriptService {

    companion object {

        // NOTE: We need to do dynamic casting here, otherwise the loading
        // of scripts can not be done using the service layer pattern
        private fun <T : Any> Any?.castOrError(clazz: KClass<T>): T =
            takeIf { clazz.isInstance(it) }?.let { clazz.javaObjectType.cast(it) }
            ?: throw IllegalArgumentException("Cannot cast ${this!!::class.java} to expected type ${clazz.jvmName}")

    }

    override fun <T : Any> loadFromPath(script: Path, clazz: KClass<T>): T =
        loadFromString(Files.readString(script), clazz)

    override fun <T : Any> loadFromString(script: String, clazz: KClass<T>): T =
        kotlin.runCatching { eval(script) }
            .getOrElse { throw ScriptException("Could not evaluate script", it) }
            .castOrError(clazz)

    private fun eval(script: String): Any? {
        val configuration = createJvmCompilationConfigurationFromTemplate<SnugScript> {
            jvm {
                dependenciesFromCurrentContext("cli")
            }
        }

        val evaluationResult = BasicJvmScriptingHost()
            .eval(script.toScriptSource(), configuration, null)

        val result = evaluationResult.valueOrNull()?.returnValue as ResultValue.Value
        return result.value
    }

}