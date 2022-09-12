package com.app.test.presentation


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.app.test.R


object CustomBindingAdapters {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun imageUrl(imageView: ImageView, url: String?) {
        url?.let {
            Glide
                .with(imageView.context)
                .load(url)
                .placeholder(R.drawable.ic_person)
                .into(imageView)
        }

    }

}

