package com.messias.gazetadev.repository

import com.messias.gazetadev.model.ContentItemResponse
import com.messias.gazetadev.network.GazetaDevApi
import com.messias.gazetadev.util.ContentType
import javax.inject.Inject

class ContentRepository @Inject constructor(
    private val gazetaDevApi: GazetaDevApi
) {
    suspend fun getContent(contentType: ContentType): List<ContentItemResponse> {
        return when (contentType){
            ContentType.ALL -> gazetaDevApi.getGeneralContent(0)
            ContentType.YOUTUBE -> gazetaDevApi.getYouTubeContent(0)
            ContentType.ARTICLES -> gazetaDevApi.getArticleContent(0)
            ContentType.PODCASTS -> gazetaDevApi.getPodcastContent(0)
            ContentType.TWITCH -> gazetaDevApi.getTwitchContent(0)
        }
    }
}