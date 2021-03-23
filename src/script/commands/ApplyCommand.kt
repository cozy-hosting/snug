package it.oechsler.script.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.types.path
import it.oechsler.script.data.ScriptCompileException
import it.oechsler.script.data.ScriptRuntimeException
import it.oechsler.script.services.ScriptService
import it.oechsler.script.language.ScriptRoot
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class ApplyCommand: CliktCommand(name = "apply", help = "Evaluate and apply a resource script"), KoinComponent {

    private val scriptService: ScriptService by inject()

    private val scriptPath by argument().path(mustExist = true, canBeDir = false)

    override fun run() {
        try {
            val script = scriptService.loadFromPath(scriptPath, ScriptRoot::class)
            script.apply()
        } catch (ex: ScriptCompileException) {
            echo("${ex.message}\n", err = true)
            ex.reports.forEach {
                echo(it, err = true)
            }
        } catch (ex: ScriptRuntimeException) {
            echo(ex.message, err = true)
        }
    }

}
