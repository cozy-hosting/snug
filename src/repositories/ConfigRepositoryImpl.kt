package it.oechsler.repositories

import it.oechsler.repositories.data.Config
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File


class ConfigRepositoryImpl : ConfigRepository {

    override val configFile: File = File(System.getProperty("user.home") + "/.snug/config.json")
    override fun get(): Config {
        return Json.decodeFromString(configFile.readText())
    }

    override fun create(config: Config) {
        configFile.mkdirs()
        configFile.createNewFile()
        configFile.writeText(Json.encodeToString(config))
    }

    override fun delete() {
        configFile.delete()
    }

}