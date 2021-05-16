package it.oechsler.script.language

import it.oechsler.script.SnugDsl
import it.oechsler.script.data.Path

@SnugDsl
class PathBuilder private constructor(private val destination: Path) {

    companion object {

        val root = Path()

        fun path(destination: Path): PathBuilder {
            return PathBuilder(destination)
        }

    }

    override fun toString(): String {
        return destination.toString()
    }

}