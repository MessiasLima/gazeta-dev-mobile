package com.messias.gazetadev.util

enum class ContentType {
    ALL, YOUTUBE, ARTICLE, PODCAST, TWITCH;

    companion object {
        fun fromString(type: String?): ContentType {
            return when (type) {
                "YOUTUBE" -> YOUTUBE
                "ARTICLE" -> ARTICLE
                "PODCAST" -> PODCAST
                "VOD",
                "LIVE" -> TWITCH
                else -> ALL
            }
        }
    }
}
