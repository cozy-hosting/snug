package it.oechsler.services

import java.nio.file.Path
import kotlin.reflect.KClass

interface ScriptService {

    fun <T: Any> loadFromPath(script: Path, clazz: KClass<T>): T

    fun <T: Any> loadFromString(script: String, clazz: KClass<T>): T

}