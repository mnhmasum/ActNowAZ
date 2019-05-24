package com.namageoff.actnowaz.features.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.namageoff.actnowaz.R
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.item_news.view.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        textView.text = intent.getStringExtra("desc")

        /*Glide
            .with(this)
            .load(article.imageURL)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(view.imageView)*/
    }
}
