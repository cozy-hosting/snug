package it.oechsler.language

@Suppress("unused")
class Greeting private constructor(private val name: String?) : ScriptRoot {

    companion object {

        private operator fun invoke(): Greeting {
            return Greeting()
        }

        fun greet(name: String): Greeting {
            return Greeting(name)
        }

        fun greet(name: String, init: Greeting.() -> Unit): Greeting {
            return Greeting(name).apply(init)
        }

    }

    private var from: String? = null

    fun from(from: String) {
        this.from = from
    }

    override fun toString(): String {
        return if (from == null)
            "Greetings, $name!"
        else
            "Greetings, $name from $from!"
    }

    override fun run() {
        println(this)
    }

}
