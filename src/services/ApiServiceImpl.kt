package it.oechsler.services

import io.ktor.http.*

class ApiServiceImpl : ApiService {

    private val rootUrl = "http://localhost:3000"

    override fun resource(path: String): Lazy<Url> {
        return lazy { Url("$rootUrl/$path") }
    }

}