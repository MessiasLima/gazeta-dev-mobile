package com.messias.gazetadev.model

import android.os.Parcelable
import com.messias.gazetadev.util.ContentType
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class ContentItem(
    val title: String?,
    val author: String?,
    val link: String?,
    val pubDate: Date?,
    val type: ContentType?,
    val thumbnailUrl: String?,
    val live: Boolean
) : Parcelable
