package com.messias.gazetadev.ui.content

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.messias.gazetadev.model.ContentItem

class ContentItemsAdapter(
    private val onItemClicked: (item: ContentItem) -> Unit
) : PagingDataAdapter<ContentItem, ContentItemViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: ContentItemViewHolder, position: Int) {
        getItem(position)?.also { contentItem ->
            holder.bind(contentItem)
            holder.itemView.setOnClickListener { onItemClicked(contentItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentItemViewHolder {
        return ContentItemViewHolder.create(parent)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ContentItem>() {
            override fun areItemsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
