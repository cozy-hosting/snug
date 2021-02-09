package it.oechsler.apply.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.types.path
import it.oechsler.apply.services.ScriptService
import it.oechsler.language.ScriptRoot
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class ApplyCommand: CliktCommand(name = "apply", help = "Evaluate and apply a resource script"), KoinComponent {

    private val scriptService: ScriptService by inject()

    private val scriptPath by argument().path(mustExist = true, canBeDir = false)

    override fun run() {
        val script = scriptService.loadFromPath(scriptPath, ScriptRoot::class)

        script.run()
    }

}
