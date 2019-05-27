package com.namageoff.actnowaz.features.info

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.namageoff.actnowaz.R
import kotlinx.android.synthetic.main.activity_details.*

@SuppressLint("Registered")
class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        supportActionBar!!.elevation = 1f
        supportActionBar!!.title = "Info"
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

      /*  textView.text = intent.getStringExtra("desc")
        textViewTitle.text = intent.getStringExtra("title")
        textViewDate.text = intent.getStringExtra("date")
        textViewURL.text = intent.getStringExtra("link")*/

        Glide
                .with(this)
                .load(R.mipmap.ic_launcher)
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
