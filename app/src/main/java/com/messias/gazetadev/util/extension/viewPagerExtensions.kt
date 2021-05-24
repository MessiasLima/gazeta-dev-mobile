package com.messias.gazetadev.util.extension

import androidx.viewpager2.widget.ViewPager2

fun ViewPager2.setOnPageChangedListener(callback: (position: Int) -> Unit) {
    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            callback(position)
        }
    })
}