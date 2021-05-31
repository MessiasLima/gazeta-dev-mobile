package com.messias.gazetadev.ui.content

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import com.messias.gazetadev.model.ContentItemResponse
import com.messias.gazetadev.repository.ContentRepository
import com.messias.gazetadev.util.ContentType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    contentRepository: ContentRepository
) : ViewModel() {
    val contentItems = savedStateHandle.getLiveData<ContentType>(
        ContentFragment.ARGUMENT_CONTENT_TYPE
    ).switchMap { contentType ->
        liveData {
            emit(contentRepository.getContent(contentType))
        }
    }
}
