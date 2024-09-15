package com.example.heroesapp

data class User(val email: String, val password: String)

object UserRepository {
    val users = listOf(
        User("user1@example.com", "password1"),
        User("user2@example.com", "password2")
    )
}
