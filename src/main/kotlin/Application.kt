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
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.sqlobject.SqlObjectPlugin
import org.jdbi.v3.sqlobject.statement.SqlQuery

// DB関連オブジェクトのインスタンス化
var jdbi = Jdbi.create("jdbc:postgresql://db:5432/atsumori_db", "user", "pass")
    .installPlugin(SqlObjectPlugin())

interface UserName{
    @SqlQuery("SELECT name from users")
    fun selectNameList(): List<String>
}

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module() {
    val client = HttpClient(Apache) {

    }

    install(Routing) {
        get("/") {
            val userName: UserName = jdbi.onDemand(UserName::class.java)
            val names = userName.selectNameList()
            call.respond(names)
        }

        helloName()
    }
}

