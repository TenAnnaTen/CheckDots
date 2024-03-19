package com.example.checkdots.utils

fun Regexp(str: String): Boolean {
    val r =
        Regex("г\\.\\s[А-Яа-я\\s-]{2,50},\\s(?:ул\\.|пер\\.|пр\\.)\\s[А-Яа-я\\s-]{2,50},\\sд\\.\\s\\d{1,3}(?:/\\d{1,3})?")
    return if (r.matches(str)) true
    else false
}