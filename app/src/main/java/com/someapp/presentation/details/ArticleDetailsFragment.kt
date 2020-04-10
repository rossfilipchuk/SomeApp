package com.someapp.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.someapp.R
import com.someapp.databinding.FragmentArticleDetailsBinding
import com.someapp.domain.Article
import kotlinx.android.synthetic.main.fragment_article_details.*

class ArticleDetailsFragment : Fragment(), FabButtonClickListener {
    private lateinit var articleDetailsViewModel: ArticleDetailsViewModel
    private lateinit var article: Article

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        articleDetailsViewModel = ViewModelProvider(this).get(ArticleDetailsViewModel::class.java)

        val binding: FragmentArticleDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_article_details, container, false)
        binding.lifecycleOwner = this
        binding.vm = articleDetailsViewModel
        binding.clickHandler = this

        return binding.root
    }

    companion object {
        fun newInstance(article: Article) =
            ArticleDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("DATA_ITEM", article)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        article = arguments?.getSerializable("DATA_ITEM") as Article
        articleDetailsViewModel.setArticle(article)

        fab.setOnClickListener { Toast.makeText(activity, "Hi", Toast.LENGTH_SHORT).show() }
    }

    override fun onFabButtonClick() {
        articleDetailsViewModel.isArticleSaved.value?.let { saved ->
            val message =
                if (saved) getString(R.string.snackbar_delete_message)
                else getString(R.string.snackbar_save_message)

            val snackBarCallback = createSnackbarCallback(saved)

            Snackbar.make(fab, message, Snackbar.LENGTH_LONG).apply {
                addCallback(snackBarCallback)
                setAction("Cancel") { removeCallback(snackBarCallback) }
            }.show()
        }
    }

    private fun createSnackbarCallback(saved: Boolean): Snackbar.Callback {
        return object : Snackbar.Callback() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)
                if (saved) articleDetailsViewModel.deleteArticle()
                else articleDetailsViewModel.saveArticle()
            }
        }
    }
}