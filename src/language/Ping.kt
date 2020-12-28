package it.oechsler.language

import io.ktor.client.*
import io.ktor.client.request.*
import it.oechsler.services.ApiService
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
@Suppress("unused")
class Ping private constructor() : KoinComponent {

    private val httpClient: HttpClient by inject()

    private val apiService: ApiService by inject()

    companion object {

        private operator fun invoke(): Ping {
            return Ping()
        }

        fun ping(): Ping {
            return Ping()
        }

    }

    override fun toString(): String {
        val requestUrl by apiService.resource("ping")

        return runBlocking {
            httpClient.get(requestUrl)
        }
    }

}