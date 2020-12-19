import com.github.ajalt.clikt.core.subcommands
import commands.HelloWorldCommand
import commands.MainCommand
import org.koin.core.context.startKoin

fun main(args: Array<String>) {
    startKoin {
        MainCommand()
            .subcommands(HelloWorldCommand())
            .main(args)
    }
}