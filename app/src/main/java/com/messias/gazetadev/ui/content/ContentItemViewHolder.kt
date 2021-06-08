package com.messias.gazetadev.ui.content

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.messias.gazetadev.R
import com.messias.gazetadev.databinding.ContentItemListItemBinding
import com.messias.gazetadev.model.ContentItem
import com.messias.gazetadev.util.ContentType
import java.text.SimpleDateFormat
import java.util.Locale

class ContentItemViewHolder private constructor(
    private val binding: ContentItemListItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    private val simpleDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    fun bind(contentItem: ContentItem) {
        with(binding) {
            contentItemAuthor.text = contentItem.author
            contentItemTitle.text = contentItem.title
            contentItemDate.text = contentItem.pubDate?.let(simpleDateFormat::format)
            loadImage(contentItem)
            contentItemIcon.setImageResource(getIcon(contentItem.type))
        }
    }

    private fun loadImage(contentItem: ContentItem) {
        val placeholder = getPlaceholderImage(contentItem.type)

        Glide.with(binding.root)
            .load(contentItem.thumbnailUrl)
            .error(placeholder)
            .placeholder(placeholder)
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

    private fun getIcon(type: ContentType?) = when (type) {
        ContentType.YOUTUBE -> R.drawable.ic_youtube
        ContentType.ARTICLE -> R.drawable.ic_articles
        ContentType.PODCAST -> R.drawable.ic_podcasts
        ContentType.TWITCH -> R.drawable.ic_twitch
        else -> R.drawable.ic_all
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
