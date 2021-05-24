package com.messias.gazetadev.ui.content

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.messias.gazetadev.model.ContentItem
import com.messias.gazetadev.util.ContentType

class ContentViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    val contentItems = savedStateHandle.getLiveData<ContentType>(ContentFragment.ARGUMENT_CONTENT_TYPE).map {
        ContentItem(it.name)
    }
}
