package it.oechsler.script.data

import org.valiktor.functions.isEmpty
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate

data class Path (
    val string: String
) {
    constructor() : this("")

    operator fun div(value: String): Path {
        return Path("$this/$value")
    }

    override fun toString(): String {
        return string
    }
}