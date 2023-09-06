package com.example.data.remote.http_client

import io.ktor.client.*

expect fun httpClient(config: HttpClientConfig<*>.()-> Unit={}):HttpClient
