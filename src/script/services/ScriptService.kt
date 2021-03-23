package it.oechsler.script.services

import it.oechsler.script.language.ScriptRoot
import java.nio.file.Path
import kotlin.reflect.KClass

interface ScriptService {

    fun <T: ScriptRoot> loadFromPath(script: Path, clazz: KClass<T>): T

    fun <T: ScriptRoot> loadFromString(script: String, clazz: KClass<T>): T

}