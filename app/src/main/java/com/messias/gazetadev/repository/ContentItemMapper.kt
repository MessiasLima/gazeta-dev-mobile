package com.messias.gazetadev.repository

import com.messias.gazetadev.model.ContentItem
import com.messias.gazetadev.model.ContentItemResponse
import com.messias.gazetadev.util.ContentType

fun ContentItemResponse.toContentItem() = ContentItem(
    title = this.title,
    author = this.author,
    link = this.link,
    pubDate = this.pubDate,
    type = ContentType.fromString(this.type),
    thumbnailUrl = this.thumbnailUrl
)
