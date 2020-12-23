package it.oechsler.repositories

import it.oechsler.repositories.data.Config
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths


class ConfigRepositoryImpl : ConfigRepository {

    override val configPath: String = System.getProperty("user.home") + "/.snug/"
    override val configFile: File = File(configPath + "config.json")
    override fun get(): Config {
        return Json.decodeFromString(configFile.readText())
    }

    override fun create(config: Config) {
        Files.createDirectory(Paths.get(configPath))
        configFile.createNewFile();
        configFile.writeText(Json.encodeToString(config))
    }

    override fun delete() {
        configFile.delete()
    }

}