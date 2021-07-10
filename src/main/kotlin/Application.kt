package atsumori.management

import atsumori.management.router.helloName
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.html.*
import kotlinx.html.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.features.*

import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import java.sql.Connection

// DB関連オブジェクトのインスタンス化
/*var jdbi = Jdbi.create("jdbc:postgresql://db:5432/atsumori_db", "user", "pass")
    .installPlugin(SqlObjectPlugin())

 */

/*
interface UserName{
    @SqlQuery("SELECT name from users")
    fun selectNameList(): List<String>
}
*/

data class User(
    val userId: Int,
    val name: String,
    val mailAddress: String,
    val password: String,
    val dataId: Int
)

fun getAllUsers(): List<User> {
    var conn: Connection? = null
    var statement: Statement? = null
    var resultSet: ResultSet? = null

    var userList: MutableList<User> = mutableListOf()

    try {
        conn = DriverManager.getConnection("jdbc:postgresql://db:5432/atsumori_db", "user", "pass")
        statement = conn?.createStatement()
        resultSet = statement?.executeQuery("select * from users")
        while (resultSet?.next() ?: false) {
            userList.add(
                User(
                    resultSet!!.getInt(1),
                    resultSet!!.getString(2)!!,
                    resultSet!!.getString(3)!!,
                    resultSet!!.getString(4)!!,
                    resultSet!!.getInt(5)
                )
            )
        }
    } finally {
        resultSet?.close()
        statement?.close()
        conn?.close()
    }

    return userList
}


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module() {
    val client = HttpClient(Apache) {

    }

    install(Routing) {
        get("/") {
            /*
            val userName: UserName = jdbi.onDemand(UserName::class.java)
            val names = userName.selectNameList()
            call.respond(names)

            */
            val users = getAllUsers()
            print(users)
        }
    }
}

