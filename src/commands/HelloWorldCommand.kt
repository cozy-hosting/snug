package commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required

class HelloWorldCommand: CliktCommand(name = "hello", help = "Says hello to whom ever you want") {

    private val name by option("-n", "--name", help = "Name of the person that is greeted").required()

    override fun run() {
        echo("Hello, $name!")
    }

}