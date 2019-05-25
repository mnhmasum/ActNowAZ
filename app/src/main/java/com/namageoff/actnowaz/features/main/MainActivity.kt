package com.namageoff.actnowaz.features.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.namageoff.actnowaz.R
import com.namageoff.actnowaz.features.details.DetailsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var list: List<NewsResponse> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewNews.layoutManager = LinearLayoutManager(this)

        var viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        viewModel.init()
        viewModel.getNewsRepository()?.observe(this, Observer {
            progressBar.visibility = GONE
            recyclerViewNews.adapter = MainAdapter(it) { news -> openDetailsActivity(news) }
        })
    }

    var openDetailsActivity = fun(value: NewsResponse) {
        val intent = Intent(this, DetailsActivity::class.java)
        var bundle = Bundle()
        bundle.putString("title", value.title)
        bundle.putString("date", value.date)
        bundle.putString("desc", value.report)
        bundle.putString("image_url", value.imageURL)
        bundle.putString("link", value.link)
        bundle.putString("phone", value.phone)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}
