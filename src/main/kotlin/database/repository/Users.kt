package atsumori.management.database.repository

data class Users(
    val userId: Int,
    val name: String,
    val mailAddress: String,
    val password: String,
    val dataId: Int
)