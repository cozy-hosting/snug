package it.oechsler.repositories

import it.oechsler.repositories.data.Config
import java.io.File


interface ConfigRepository {
    val configPath: String
    val configFile: File
    public fun get(): Config
    public fun create(config: Config)
    public fun delete()
}