package com.java.`in`.utils

import com.java.`in`.model.Book
import com.java.`in`.model.User

object Constants {
    const val ADMIN_ID = "system"
    const val ADMIN_PASSWORD = "system"
    val list = mutableListOf<User>(
        User("aiyu", "aiyu.exe")
            .apply {
                borrowedBooks = mutableListOf(
                    Book(156, "Programming in C", "12/5/2021", "6/6/2021"),
                    Book(256, "Programming in Java", "1/6/2021", "12/6/2021")
                )
            },
        User("roushan", "roushan.exe")
            .apply {
                borrowedBooks = mutableListOf(
                    Book(567, "Programming in HTML", "4/5/2021", "6/5/2021"),
                )
            },
        User("aasif", "aasif.exe"),
        User("nilchor", "nilchor")
            .apply {
                borrowedBooks = mutableListOf(
                    Book(876, "How To Earn More Xp In Valorant", "31/7/2021", "5/9/2021"),
                    Book(864, "Cracking the Xp Hunting Competition", "31/6/2021", "12/7/2021")
                )
            },
        User("littu", "littu")
            .apply {
                borrowedBooks = mutableListOf(
                    Book(689, "How to Make Anime", "16/7/2021", "06/9/2021"),
                )
            },
        User("aaditya", "aaditya")
            .apply {
                borrowedBooks = mutableListOf(
                    Book(224, "10 Best Street Food", "12/6/2021", "06/9/2021"),
                )
            }
    )
}