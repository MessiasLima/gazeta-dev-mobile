package com.messias.gazetadev.repository

import com.messias.gazetadev.model.ContentItem
import com.messias.gazetadev.network.GazetaDevApi
import com.messias.gazetadev.util.ContentType
import javax.inject.Inject

class ContentRepository @Inject constructor(
    private val gazetaDevApi: GazetaDevApi
) {
    suspend fun getContent(contentType: ContentType, page: Int = 0): List<ContentItem> {
        val responseItems = when (contentType) {
            ContentType.ALL -> gazetaDevApi.getGeneralContent(page)
            ContentType.YOUTUBE -> gazetaDevApi.getYouTubeContent(page)
            ContentType.ARTICLE -> gazetaDevApi.getArticleContent(page)
            ContentType.PODCAST -> gazetaDevApi.getPodcastContent(page)
            ContentType.TWITCH -> gazetaDevApi.getTwitchContent(page)
        }

        return responseItems.map { it.toContentItem() }
    }
}
