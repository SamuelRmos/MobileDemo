package com.samuelrmos.mobiledemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform