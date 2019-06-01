package com.namageoff.actnowaz.features.main

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.namageoff.actnowaz.features.details.DetailsActivity
import com.namageoff.actnowaz.features.info.InfoActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import com.namageoff.actnowaz.R
import com.namageoff.actnowaz.alarm.AlarmBroadcastReceiver


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerViewNews.setLayoutManager(mLayoutManager)

        var viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        viewModel.init()
        viewModel.getNewsRepository()?.observe(this, Observer {
            progressBar.visibility = GONE
            recyclerViewNews.adapter = MainAdapter(it.reversed()) { news -> openDetailsActivity(news) }
        })

        var alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmBroadcastReceiver::class.java)
        var pendingIntent = PendingIntent.getBroadcast(
            this, 100,
            intent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY)
        calendar.set(Calendar.HOUR_OF_DAY, 9)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 10)


        val myIntent = Intent(this@MainActivity, AlarmBroadcastReceiver::class.java)

        val isWorking = PendingIntent.getBroadcast(this@MainActivity, 0, myIntent, PendingIntent.FLAG_NO_CREATE) != null
        if (isWorking) {
            Log.d("alarm", "is working")
        } else {
            Log.d("alarm", "is not working")
        }

        if (!isWorking) {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY *   7,
                pendingIntent
            )
        }

        //if (calendar.timeInMillis < System.currentTimeMillis()) {
            /* Alarm will be triggered exactly at 9:00 AM on Tuesday every day */

       // }

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_info -> {
            startActivity(Intent(this, InfoActivity::class.java))
            true
        }
        else -> super.onOptionsItemSelected(item)

    }

}
