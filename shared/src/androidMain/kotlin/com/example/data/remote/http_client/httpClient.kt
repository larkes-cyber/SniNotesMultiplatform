package com.example.data.remote.http_client

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import java.util.concurrent.TimeUnit

actual fun httpClient(config: HttpClientConfig<*>.()-> Unit) = HttpClient(OkHttp) {

    engine {
        config {
            retryOnConnectionFailure(true)
            connectTimeout(5, TimeUnit.SECONDS)
        }
    }
}