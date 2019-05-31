package com.namageoff.actnowaz.features.details

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.namageoff.actnowaz.R
import kotlinx.android.synthetic.main.activity_details.*
import java.text.ParseException
import java.text.SimpleDateFormat


class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        supportActionBar!!.elevation = 1f
        supportActionBar!!.title = intent.getStringExtra("title")
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val date = intent.getStringExtra("date")
        val input = SimpleDateFormat("yy-MM-dd")
        val output = SimpleDateFormat("dd MMM yyyy")
        try {
            textViewDate.text = (output.format(input.parse(date)))    // format output
        } catch (e: ParseException) {
            e.printStackTrace()
            textViewDate.text = intent.getStringExtra("date")
        }

        textView.text = intent.getStringExtra("desc")
        textViewTitle.text = intent.getStringExtra("title")
        textViewURL.text = intent.getStringExtra("link")

        Glide.with(this)
             .load(intent.getStringExtra("image_url"))
             .centerCrop()
             .placeholder(R.drawable.ic_launcher_background)
             .into(imageView2)
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId === android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(menuItem)
    }
}
