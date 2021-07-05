package atsumori.management.models

import io.ktor.locations.*

@Location("/hello/{name}")
data class HelloName(val name: String)