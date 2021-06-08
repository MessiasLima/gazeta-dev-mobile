package com.messias.gazetadev.ui.iab

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.messias.gazetadev.R
import com.messias.gazetadev.databinding.ActivityIabBinding
import com.messias.gazetadev.model.ContentItem

class IabActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIabBinding
    private val contentItem
        get() = intent.extras?.getParcelable<ContentItem>(EXTRA_CONTENT_ITEM)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIabBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {
        setupToolbar()
        setupWebView()
    }

    private fun setupToolbar() = with(binding.iabToolbar) {
        setNavigationOnClickListener {
            finish()
        }

        contentItem?.also {
            title = it.title
            subtitle = it.author
        }

        setOnMenuItemClickListener {
            if (it.itemId == R.id.iab_menu_open_externally) {
                contentItem?.link?.also { link ->
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
                }
                return@setOnMenuItemClickListener true
            }

            return@setOnMenuItemClickListener false
        }
    }

    private fun setupWebView() = with(binding.iabWebView) {
        webViewClient = WebViewClient()

        contentItem?.link?.also {
            loadUrl(it)
        }

        Unit
    }

    companion object {
        const val EXTRA_CONTENT_ITEM = "extraContentItem"
    }
}
