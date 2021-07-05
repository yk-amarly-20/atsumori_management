package atsumori.management

import atsumori.management.router.helloName
import com.sun.tools.javac.file.Locations
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.html.*
import kotlinx.html.*
import kotlinx.css.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.features.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module() {
    val client = HttpClient(Apache) {

    }

    install(Routing) {
        get("/") {
            call.respond("This is root!")
        }

        helloName()
    }
}

