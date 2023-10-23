package com.github.mn

import io.micronaut.context.annotation.Context
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpRequest
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.ClientFilter
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.ResponseFilter
import io.micronaut.http.client.annotation.Client
import org.slf4j.LoggerFactory

@Context
class MainService(
    private val httpBinClient: HttpBinClient,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun getJson(): String {
        log.debug("getJson()")
        return httpBinClient.getJson().apply {
            headers.forEach { k, v ->
                log.debug("getJson() - header: $k, value: $v")
            }
        }.body().apply {
            log.debug("getJson() - result: $this")
        }
    }
}

@Client("httpbin")
interface HttpBinClient {

    @Get("/json")
    fun getJson(): HttpResponse<String>
}

@ClientFilter
internal class HttpBinFilter {

    private val log = LoggerFactory.getLogger(javaClass)

    @ResponseFilter
    fun filter(request: MutableHttpRequest<*>, response: MutableHttpResponse<*>) {
        log.debug("filter() - request: ${request::class.java.name}")
        log.debug("filter() - response: ${response::class.java.name}")
        response.headers.set("content-type", "text/plain")
    }
}
