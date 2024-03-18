package com.example.checkdots.utils

fun Regexp(str: String): Boolean {
    val r =
        Regex("г\\.\\s[А-Я][а-я\\s-]{2,25},\\s(?:ул\\.|пер\\.|пр\\.)\\s[А-Я][а-я\\s-]{2,35},\\sд\\.\\s(?:[0-9]{1,3}|[0-9]{1,3}/[0-9]{1,3})")
    return if (r.matches(str)) true
    else false
}