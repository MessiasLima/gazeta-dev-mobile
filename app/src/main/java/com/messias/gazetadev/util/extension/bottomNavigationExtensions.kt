package com.messias.gazetadev.util.extension

import android.view.MenuItem
import androidx.core.view.forEachIndexed
import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.indexOf(menuItem: MenuItem): Int {
    var result = 0
    menu.forEachIndexed { index, item ->
        if (item == menuItem) {
            result = index
        }
    }
    return result
}