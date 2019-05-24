package com.namageoff.actnowaz.features.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.namageoff.actnowaz.R
import kotlinx.android.synthetic.main.item_news.view.*

class MainAdapter(
        private val characters: List<NewsResponse>,
        private val itemClick: (NewsResponse) -> Unit) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.show(characters[pos])
    }

    override fun getItemCount() = characters.size

    class ViewHolder(private val view: View, private val itemClick: (NewsResponse) -> Unit) : RecyclerView.ViewHolder(view) {
        fun show(article: NewsResponse) {
            Glide
                    .with(view.context)
                    .load(article.imageURL)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(view.imageView)

            view.setOnClickListener { itemClick(article) }
            view.textTitle.text = article.title
            view.textPublishedAt.text = article.date
            view.textShortDescription.text = article.description
        }
    }
}
