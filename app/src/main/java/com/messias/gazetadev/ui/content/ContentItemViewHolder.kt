package com.messias.gazetadev.ui.content

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.messias.gazetadev.databinding.ContentItemListItemBinding
import com.messias.gazetadev.model.ContentItem

class ContentItemViewHolder private constructor(
    private val binding: ContentItemListItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(contentItem: ContentItem) {
        with(binding) {
            contentItemAuthor.text = contentItem.author
            contentItemTitle.text = contentItem.title
            contentItemDate.text = contentItemDate.toString()
        }
    }

    companion object {
        fun create(viewGroup: ViewGroup): ContentItemViewHolder {
            val binding = ContentItemListItemBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )

            return ContentItemViewHolder(binding)
        }
    }
}
