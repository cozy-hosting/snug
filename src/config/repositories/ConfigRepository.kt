package it.oechsler.config.repositories

import it.oechsler.config.data.Config


interface ConfigRepository {

    fun retrieve(): Config?

    fun create(config: Config)

    fun delete()

}
