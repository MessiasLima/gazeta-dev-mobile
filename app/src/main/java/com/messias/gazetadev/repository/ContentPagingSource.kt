package com.messias.gazetadev.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.messias.gazetadev.model.ContentItem
import com.messias.gazetadev.util.ContentType

class ContentPagingSource constructor(
    private val contentRepository: ContentRepository,
    private val contentType: ContentType?
) : PagingSource<Int, ContentItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ContentItem> {
        if (contentType == null) {
            return LoadResult.Error(Throwable("Invalid content type"))
        }

        return try {
            val nextPageNumber = params.key ?: 0
            val response = contentRepository.getContent(contentType, nextPageNumber)
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = (nextPageNumber) + 1
            )
        } catch (throwable: Throwable) {
            LoadResult.Error(throwable)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ContentItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
