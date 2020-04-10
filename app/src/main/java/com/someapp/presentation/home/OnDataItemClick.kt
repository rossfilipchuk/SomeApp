package com.someapp.presentation.home

import android.view.View
import com.someapp.domain.Article

interface OnDataItemClickListener {
    fun onDataItemClickListener(article: Article, transitionView: View)
}