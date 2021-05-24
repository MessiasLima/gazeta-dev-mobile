package com.messias.gazetadev.ui.main

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.messias.gazetadev.R
import com.messias.gazetadev.databinding.ActivityMainBinding
import com.messias.gazetadev.util.extension.indexOf
import com.messias.gazetadev.util.extension.setOnPageChangedListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private val bottomNavigation by lazy { binding.mainBottomNavigation }
    private val viewPager by lazy { binding.mainViewPager }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initObservers()
    }

    private fun initViews() {
        initBottomNavigation()
        initViewPager()
    }

    private fun initBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            viewPager.currentItem = bottomNavigation.indexOf(menuItem)
            viewModel.onItemSelected(menuItem.itemId)
            true
        }
    }

    private fun initViewPager() {
        with(viewPager) {
            adapter = MainFragmentAdapter(this@MainActivity)
            setOnPageChangedListener { position ->
                bottomNavigation.selectedItemId = bottomNavigation.menu.getItem(position).itemId
            }
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
        with(bottomNavigation) {
            itemIconTintList = ColorStateList.valueOf(itemColor)
            itemTextColor = ColorStateList.valueOf(itemColor)
        }
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

