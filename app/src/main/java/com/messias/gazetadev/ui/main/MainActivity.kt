package com.messias.gazetadev.ui.main

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.messias.gazetadev.R
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

    // region init views
    private fun initViews() {
        initBottomNavigation()
        initViewPager()
    }

    private fun initBottomNavigation() {
        binding.mainBottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            viewModel.onItemSelected(menuItem.itemId)
            true
        }
    }

    private fun initViewPager() {
        binding.mainViewPager.adapter = MainFragmentAdapter(this)
    }
    // endregion

    // region init observers
    private fun initObservers() {
        initItemSelectedObserver()
    }

    private fun initItemSelectedObserver() {
        viewModel.selectedItem.observe(this) { updateBottomNavigationColor(it) }
    }

    private fun updateBottomNavigationColor(itemId: Int) {
        val itemColor = getItemColor(itemId)
        binding.mainBottomNavigation.itemIconTintList = ColorStateList.valueOf(itemColor)
        binding.mainBottomNavigation.itemTextColor = ColorStateList.valueOf(itemColor)
    }
    // endregion

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

