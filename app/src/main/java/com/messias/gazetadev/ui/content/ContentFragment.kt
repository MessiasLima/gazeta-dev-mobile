package com.messias.gazetadev.ui.content

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.messias.gazetadev.R
import com.messias.gazetadev.databinding.FragmentContentBinding
import com.messias.gazetadev.ui.iab.IabActivity
import com.messias.gazetadev.util.ContentType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContentFragment : Fragment() {
    private lateinit var binding: FragmentContentBinding
    private lateinit var contentItemsAdapter: ContentItemsAdapter
    private val viewModel by viewModels<ContentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() {
        initRecyclerView()
        initToolbar()
    }

    private fun initToolbar() {
        viewModel.contentType.observe(viewLifecycleOwner) {
            binding.contentCollapsingToolbar.title = getString(getWindowTitle(it))
        }
    }

    private fun getWindowTitle(contentType: ContentType) = when (contentType) {
        ContentType.ALL -> R.string.app_name
        ContentType.YOUTUBE -> R.string.youtube
        ContentType.ARTICLE -> R.string.articles
        ContentType.PODCAST -> R.string.podcasts
        ContentType.TWITCH -> R.string.twitch
    }

    private fun initRecyclerView() {
        contentItemsAdapter = ContentItemsAdapter {
            startActivity(Intent(requireContext(), IabActivity::class.java))
        }

        contentItemsAdapter.addLoadStateListener {
            binding.contentProgressBar.isVisible = it.refresh is LoadState.Loading
        }

        with(binding.contentRecyclerView) {
            layoutManager = StaggeredGridLayoutManager(
                resources.getInteger(R.integer.span_count),
                StaggeredGridLayoutManager.VERTICAL
            )
            itemAnimator = DefaultItemAnimator()
            adapter = contentItemsAdapter
        }
    }

    private fun initObservers() {
        viewModel.contentItems.observe(viewLifecycleOwner) { pagingData ->
            contentItemsAdapter.submitData(lifecycle, pagingData)
        }
    }

    companion object {
        const val ARGUMENT_CONTENT_TYPE = "contentType"

        fun newInstance(contentType: ContentType) = ContentFragment().apply {
            arguments = bundleOf(
                ARGUMENT_CONTENT_TYPE to contentType
            )
        }
    }
}
