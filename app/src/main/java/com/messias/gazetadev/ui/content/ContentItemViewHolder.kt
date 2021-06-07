package com.messias.gazetadev.ui.content

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.Target
import com.messias.gazetadev.R
import com.messias.gazetadev.databinding.ContentItemListItemBinding
import com.messias.gazetadev.model.ContentItem
import com.messias.gazetadev.util.ContentType

class ContentItemViewHolder private constructor(
    private val binding: ContentItemListItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(contentItem: ContentItem) {
        with(binding) {
            contentItemAuthor.text = contentItem.author
            contentItemTitle.text = contentItem.title
            contentItemDate.text = contentItemDate.toString()
            loadImage(contentItem)
        }
    }

    private fun loadImage(contentItem: ContentItem) {
        val placeholder = getPlaceholderImage(contentItem.type)

        Glide.with(binding.root)
            .load(contentItem.thumbnailUrl)
            .error(placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(placeholder)
            .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            .into(binding.contentItemImage)
    }

    private fun getPlaceholderImage(type: ContentType?) = when (type) {
        ContentType.ALL -> R.drawable.default_thumbnail_all
        ContentType.YOUTUBE -> R.drawable.default_thumbnail_youtube
        ContentType.ARTICLE -> R.drawable.default_thumbnail_article
        ContentType.PODCAST -> R.drawable.default_thumbnail_podcast
        ContentType.TWITCH -> R.drawable.default_thumbnail_twitch
        else -> R.drawable.default_thumbnail_all
    }

    companion object {
        private const val TAG = "ContentItemViewHolder"
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
