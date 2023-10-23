package com.github.mn

import io.micronaut.context.annotation.Context
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import org.slf4j.LoggerFactory

@Context
class MainService(
    private val httpBinClient: HttpBinClient,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun getJson() : String {
        log.debug("getJson()")
        return httpBinClient.getJson().body().apply {
            log.debug("getJson() - result: $this")
        }
    }
}

@Client("httpbin")
interface HttpBinClient {

    @Get("/json")
    fun getJson(): HttpResponse<String>
}
