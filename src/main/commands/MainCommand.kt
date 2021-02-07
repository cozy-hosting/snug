package it.oechsler.main.commands

import com.github.ajalt.clikt.core.NoOpCliktCommand

class MainCommand : NoOpCliktCommand(
    name = "snug",
    help = "snug adj.\tComfortably sheltered and warm; cozy.",
    printHelpOnEmptyArgs = true
)
