import it.oechsler.language.ScriptRoot

println("This is some stuff that I defined!")

object : ScriptRoot {

    override fun run() {
        println("Hello World")
    }

}
