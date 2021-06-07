package com.messias.gazetadev.ui

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.messias.gazetadev.R
import com.messias.gazetadev.databinding.ActivityMainBinding
import com.messias.gazetadev.ui.main.MainFragmentAdapter
import com.messias.gazetadev.ui.main.MainViewModel
import com.messias.gazetadev.util.ContentType
import com.messias.gazetadev.util.extension.indexOf
import com.messias.gazetadev.util.extension.removeOnPageChangedListener
import com.messias.gazetadev.util.extension.setOnPageChangedListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var onPageChangeCallback: ViewPager2.OnPageChangeCallback
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
            offscreenPageLimit = ContentType.values().size
            adapter = MainFragmentAdapter(this@MainActivity)
            onPageChangeCallback = setOnPageChangedListener { position ->
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

    override fun onDestroy() {
        super.onDestroy()
        viewPager.removeOnPageChangedListener(onPageChangeCallback)
        viewPager.adapter = null
    }
}
