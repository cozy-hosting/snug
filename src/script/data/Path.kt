package it.oechsler.script.data


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