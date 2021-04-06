package it.oechsler.script.language

import it.oechsler.script.data.Path
import it.oechsler.script.language.PathBuilder.Companion.path
import it.oechsler.script.language.PathBuilder.Companion.root

@Suppress("unused")
class PathBuilder private constructor(private val destination: Path) {

    companion object {

        fun root() = Path()

        fun path(destination: Path): PathBuilder {
            return PathBuilder(destination)
        }

    }

    override fun toString(): String {
        return destination.toString()
    }

}