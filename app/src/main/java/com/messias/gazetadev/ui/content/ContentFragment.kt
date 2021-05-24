package com.messias.gazetadev.ui.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.messias.gazetadev.databinding.FragmentContentBinding
import com.messias.gazetadev.util.ContentType

class ContentFragment : Fragment() {
    private lateinit var binding: FragmentContentBinding
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
        initObservers()
    }

    private fun initObservers() {
        viewModel.contentItems.observe(viewLifecycleOwner) {
            binding.text.text = it.name
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