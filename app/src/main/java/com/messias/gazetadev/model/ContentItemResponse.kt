package com.messias.gazetadev.model

import java.util.Date

data class ContentItemResponse(
    val title: String?,
    val author: String?,
    val link: String?,
    val pubDate: Date?,
    val type: String?,
    val sourceId: String?,
    val thumbnailUrl: String?
)
