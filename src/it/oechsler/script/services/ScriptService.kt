package it.oechsler.script.services

import it.oechsler.script.language.Script
import java.nio.file.Path
import kotlin.reflect.KClass

interface ScriptService {

    fun <T: Script> loadFromPath(script: Path, clazz: KClass<T>): T

    fun <T: Script> loadFromString(script: String, clazz: KClass<T>): T

}