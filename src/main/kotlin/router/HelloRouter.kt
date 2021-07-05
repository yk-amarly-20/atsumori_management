package atsumori.management.router

import atsumori.management.models.HelloName
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.helloName() {
    get<HelloName> { param ->
        val name = param.name
        call.respond("hello ${name}")
    }
}