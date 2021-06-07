package com.messias.gazetadev.ui.content

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.messias.gazetadev.repository.ContentPagingSource
import com.messias.gazetadev.repository.ContentRepository
import com.messias.gazetadev.util.ContentType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    contentRepository: ContentRepository
) : ViewModel() {
    val contentType = savedStateHandle.getLiveData<ContentType>(
        ContentFragment.ARGUMENT_CONTENT_TYPE
    )

    val contentItems = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            ContentPagingSource(
                contentRepository,
                savedStateHandle.get<ContentType>(ContentFragment.ARGUMENT_CONTENT_TYPE)
            )
        }
    ).liveData.cachedIn(this)
}
