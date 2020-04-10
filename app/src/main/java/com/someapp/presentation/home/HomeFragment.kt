package com.someapp.presentation.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.transition.Fade
import com.someapp.R
import com.someapp.domain.Article
import com.someapp.presentation.details.ArticleDetailsFragment
import com.someapp.presentation.home.adapter.ArticlesAdapter
import com.someapp.toast
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), OnDataItemClickListener {
    private lateinit var homeViewModel: HomeViewModel

    private val articlesAdapter =
        ArticlesAdapter(emptyList(), this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvArticlesList.adapter = articlesAdapter

        homeViewModel.articles.observe(viewLifecycleOwner, Observer { resources ->
            when {
                resources.isSuccess() -> {
                    swipeContainer.isRefreshing = false
                    resources.data?.let { dataList ->
                        if (dataList.isEmpty()) context?.toast("No data available. You should have connection to load some")
                        else articlesAdapter.updateData(dataList)
                    }
                }
                resources.isError() -> {
                    swipeContainer.isRefreshing = false
                    if (articlesAdapter.isDataEmpty()) homeViewModel.loadSavedNews()
                    else context?.toast("Update error has occurred")
                }
                resources.isLoading() -> {
                    swipeContainer.isRefreshing = true
                }
            }
        })

        homeViewModel.loadNews()

        swipeContainer.setOnRefreshListener {
            homeViewModel.loadNews()
        }
    }

    override fun onDataItemClickListener(article: Article, transitionView: View) {
        exitTransition = Fade()

        val endFragment = ArticleDetailsFragment.newInstance(article)
        endFragment.enterTransition = Fade()

        parentFragmentManager.beginTransaction()
            .add(R.id.fragment_container, endFragment)
            .addToBackStack(null)
            .commit()
    }
}