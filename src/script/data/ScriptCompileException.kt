package it.oechsler.script.data

import kotlin.script.experimental.api.ScriptDiagnostic

class ScriptCompileException(
    override val message: String,
    val reports: List<ScriptDiagnostic>
) : Exception(message)