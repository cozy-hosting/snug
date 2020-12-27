package it.oechsler.repositories

import it.oechsler.repositories.data.Config
import java.io.File


interface ConfigRepository {

    fun retrieve(): Config?

    fun create(config: Config)

    fun delete()

}
