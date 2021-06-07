package com.messias.gazetadev.ui.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.messias.gazetadev.databinding.FragmentContentBinding
import com.messias.gazetadev.ui.MainActivity
import com.messias.gazetadev.util.ContentType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContentFragment : Fragment() {
    private lateinit var binding: FragmentContentBinding
    private lateinit var adapter: ContentItemsAdapter
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
    }

    private fun initRecyclerView() {
        adapter = ContentItemsAdapter()
        binding.contentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.contentRecyclerView.itemAnimator = DefaultItemAnimator()
        binding.contentRecyclerView.adapter = adapter
    }

    private fun initObservers() {
        viewModel.contentItems.observe(viewLifecycleOwner) { pagingData ->
            adapter.submitData(lifecycle, pagingData)
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
