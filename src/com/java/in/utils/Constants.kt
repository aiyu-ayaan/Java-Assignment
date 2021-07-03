package com.java.`in`.utils

import com.java.`in`.model.Book
import com.java.`in`.model.User

object Constants {
    const val ADMIN_ID = "system"
    const val ADMIN_PASSWORD = "system"
    val list = listOf<User>(
        User("Aiyu", "aiyu.exe")
            .apply {
                borrowedBooks = listOf(
                    Book(156, "Programming in C", "12/5/2021", "06/06/2021"),
                    Book(256, "Programming in Java", "01/6/2021", "12/06/2021")
                )
            }
    )
}