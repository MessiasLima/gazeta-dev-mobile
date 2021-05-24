package com.messias.gazetadev.util.extension

import androidx.viewpager2.widget.ViewPager2

fun ViewPager2.setOnPageChangedListener(callback: (position: Int) -> Unit): ViewPager2.OnPageChangeCallback {
    val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            callback(position)
        }
    }
    registerOnPageChangeCallback(onPageChangeCallback)
    return onPageChangeCallback
}

fun ViewPager2.removeOnPageChangedListener(onPageChangeCallback: ViewPager2.OnPageChangeCallback) {
    unregisterOnPageChangeCallback(onPageChangeCallback)
}
