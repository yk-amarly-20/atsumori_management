package atsumori.management.database.queries

const val getAllUsersSql = "SELECT * FROM users"

val getUsersByUserId = "SELECT * FROM users where userId = :userId"


