package it.oechsler.script.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.types.path
import it.oechsler.script.language.ScriptRoot
import it.oechsler.script.services.ScriptService
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class RollbackCommand: CliktCommand(name = "rollback", help = "Evaluate and rollback a resource script"), KoinComponent {

    private val scriptService: ScriptService by inject()

    private val scriptPath by argument().path(mustExist = true, canBeDir = false)

    override fun run() {
        val script = scriptService.loadFromPath(scriptPath, ScriptRoot::class)

        script.rollback()
    }

}