package com.someapp.framework.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.cleanarchitecturesample.framework.databinding.BindingTags
import com.someapp.R
import com.someapp.getImageUrlOrDefault
import com.squareup.picasso.Picasso


object BindingAdapters {

    @JvmStatic
    @BindingAdapter(BindingTags.bindImageUrl)
    fun bindImageUrl(imageView: ImageView, url: String?) {
        Picasso.get()
            .load(getImageUrlOrDefault(url))
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(imageView)
    }
}