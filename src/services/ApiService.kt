package it.oechsler.services

import io.ktor.http.*

interface ApiService {

    fun resource(path: String): Lazy<Url>

}