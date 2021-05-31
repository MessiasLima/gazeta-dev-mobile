package com.messias.gazetadev.model

import com.messias.gazetadev.util.ContentType
import java.util.Date

data class ContentItem(
    val title: String?,
    val author: String?,
    val link: String?,
    val pubDate: Date?,
    val type: ContentType?
)
