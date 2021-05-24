package com.messias.gazetadev

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.messias.gazetadev.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initObservers()
    }

    private fun initViews() {
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            viewModel.onItemSelected(menuItem.itemId)
            true
        }
    }

    private fun initObservers() {
        initItemSelectedObserver()
    }

    private fun initItemSelectedObserver() {
        viewModel.selectedItem.observe(this) { updateBottomNavigationColor(it) }
    }

    private fun updateBottomNavigationColor(itemId: Int) {
        val itemColor = getItemColor(itemId)
        binding.bottomNavigation.itemIconTintList = ColorStateList.valueOf(itemColor)
        binding.bottomNavigation.itemTextColor = ColorStateList.valueOf(itemColor)
    }

    private fun getItemColor(itemId: Int): Int {
        val colorRes = when (itemId) {
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
