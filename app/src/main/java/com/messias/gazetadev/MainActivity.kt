package com.messias.gazetadev

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.messias.gazetadev.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            updateBottomNavigationColor(menuItem)
            true
        }
    }

    private fun updateBottomNavigationColor(menuItem: MenuItem) {
        val itemColor = getItemColor(menuItem)
        binding.bottomNavigation.itemIconTintList = ColorStateList.valueOf(itemColor)
        binding.bottomNavigation.itemTextColor = ColorStateList.valueOf(itemColor)
    }

    private fun getItemColor(menuItem: MenuItem): Int {
        val colorRes = when(menuItem.itemId) {
            R.id.main_menu_all -> R.color.all
            R.id.main_menu_youtube -> R.color.youtube
            R.id.main_menu_articles -> R.color.articles
            R.id.main_menu_podcasts -> R.color.podcasts
            R.id.main_menu_twitch -> R.color.twitch
            else -> R.color.black
        }

        return ContextCompat.getColor(this, colorRes)
    }
}
