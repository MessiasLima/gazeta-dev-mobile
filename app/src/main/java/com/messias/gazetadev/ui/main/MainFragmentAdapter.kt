package com.messias.gazetadev.ui.main

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.messias.gazetadev.ui.content.ContentFragment
import com.messias.gazetadev.util.ContentType

class MainFragmentAdapter(activity: MainActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = ContentType.values().size

    override fun createFragment(position: Int) = ContentFragment.newInstance(ContentType.values()[position])
}