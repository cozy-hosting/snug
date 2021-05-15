package it.oechsler.script.services

import it.oechsler.script.SnugScript
import it.oechsler.script.data.*
import it.oechsler.script.language.Script
import java.nio.file.Files
import java.nio.file.Path
import kotlin.reflect.KClass
import kotlin.script.experimental.api.ResultValue
import kotlin.script.experimental.api.ScriptDiagnostic
import kotlin.script.experimental.api.valueOrNull
import kotlin.script.experimental.host.toScriptSource
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost

class ScriptServiceImpl : ScriptService {

    companion object {

        // NOTE: We need to do dynamic casting here, otherwise the loading
        // of scripts can not be done using the service layer pattern
        private fun <T : Script> Any?.toEntrypoint(clazz: KClass<T>): T =
            takeIf { clazz.isInstance(it) }?.let { clazz.javaObjectType.cast(it) }
                ?: throw ScriptRuntimeException("Script does not have a valid entrypoint")

    }

    override fun <T : Script> loadFromPath(script: Path, clazz: KClass<T>): T =
        loadFromString(Files.readString(script), clazz)

    override fun <T : Script> loadFromString(script: String, clazz: KClass<T>): T =
        kotlin.runCatching { eval(script) }
            .getOrThrow()
            .toEntrypoint(clazz)

    private fun eval(script: String): Any? {
        val host = BasicJvmScriptingHost()
        val result = host.evalWithTemplate<SnugScript>(script.toScriptSource())

        val errors = result.reports
            .filter { it.severity == ScriptDiagnostic.Severity.ERROR }
            .toList()
        if (errors.isNotEmpty()) {
            throw ScriptCompileException("Script did not compile successfully", errors)
        }

        return (result.valueOrNull()?.returnValue as? ResultValue.Value)?.value
    }

}