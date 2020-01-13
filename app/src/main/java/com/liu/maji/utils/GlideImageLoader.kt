package com.liu.maji.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.youth.banner.loader.ImageLoader

class GlideImageLoader : ImageLoader(){
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        println("加载图片$path")
        Glide.with(context).load(path).into(imageView)
    }
}