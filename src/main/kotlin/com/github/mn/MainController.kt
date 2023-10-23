package com.github.mn

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import org.slf4j.LoggerFactory

@Controller
class MainController(
    private val mainService: MainService,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Get("/json")
    @Produces(MediaType.TEXT_PLAIN)
    fun getJson(): HttpResponse<String> {
        log.debug("getJson()")
        return HttpResponse.ok(mainService.getJson())
    }
}