package atsumori.management.database.dao

import atsumori.management.database.repository.Users
import atsumori.management.database.queries.*
import org.jdbi.v3.sqlobject.statement.SqlQuery

interface UserDao {
    @SqlQuery(getAllUsersSql)
    fun getAllUsers() : List<Users>
}