package com.someapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.someapp.databinding.ItemArticleBinding
import com.someapp.domain.Article
import com.someapp.presentation.home.OnDataItemClickListener

class ArticlesAdapter(private var articles: List<Article>, private val listener: OnDataItemClickListener) :
    androidx.recyclerview.widget.RecyclerView.Adapter<NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): NewsViewHolder {
        val binding: ItemArticleBinding = ItemArticleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return NewsViewHolder(binding.root)
    }

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[holder.adapterPosition]
        holder.binding.article = article
        holder.binding.clickHandler = listener
    }

    fun updateData(newData: List<Article>) {
        if (articles != newData) {
            articles = newData
            notifyDataSetChanged()
        }
    }

    fun isDataEmpty() : Boolean = articles.isEmpty()
}

class NewsViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    val binding: ItemArticleBinding = DataBindingUtil.bind(view)!!
}