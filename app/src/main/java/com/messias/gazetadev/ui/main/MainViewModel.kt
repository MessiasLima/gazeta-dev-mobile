package com.messias.gazetadev.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _selectedItem = MutableLiveData<Int>()
    val selectedItem: LiveData<Int> = _selectedItem

    fun onItemSelected(itemId: Int) {
        _selectedItem.value = itemId
    }
}