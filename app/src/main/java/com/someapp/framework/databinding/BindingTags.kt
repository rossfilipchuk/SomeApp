package com.example.cleanarchitecturesample.framework.databinding

import androidx.annotation.StringDef

@StringDef(
    BindingTags.bindImageUrl
)

@Retention(AnnotationRetention.SOURCE)
annotation class BindingTags {
    companion object {
        const val bindImageUrl = "bindImageUrl"
    }
}