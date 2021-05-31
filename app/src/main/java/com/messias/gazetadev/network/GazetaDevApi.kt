package com.messias.gazetadev.network

import com.messias.gazetadev.model.ContentItemResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GazetaDevApi {
    @GET("api/feeds/all")
    suspend fun getGeneralContent(@Query("page") page: Int): List<ContentItemResponse>

    @GET("api/feeds/youtube")
    suspend fun getYouTubeContent(@Query("page") page: Int): List<ContentItemResponse>

    @GET("api/feeds/articles")
    suspend fun getArticleContent(@Query("page") page: Int): List<ContentItemResponse>

    @GET("api/feeds/podcasts")
    suspend fun getPodcastContent(@Query("page") page: Int): List<ContentItemResponse>

    @GET("api/feeds/twitch")
    suspend fun getTwitchContent(@Query("page") page: Int): List<ContentItemResponse>
}