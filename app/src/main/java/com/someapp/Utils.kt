package com.someapp

fun getImageUrlOrDefault(url: String?): String {
    return if (url.isNullOrEmpty())
        "https://picsum.photos/id/45/700/400"
    else
        url
}