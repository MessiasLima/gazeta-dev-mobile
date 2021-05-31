package com.messias.gazetadev.ui.content

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.messias.gazetadev.model.ContentItem
import com.messias.gazetadev.repository.ContentRepository
import com.messias.gazetadev.util.ContentType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    contentRepository: ContentRepository
) : ViewModel() {
    val contentItems = savedStateHandle.getLiveData<ContentType>(ContentFragment.ARGUMENT_CONTENT_TYPE).map {
        ContentItem(it.name)
    }
}
