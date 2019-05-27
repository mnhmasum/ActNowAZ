package com.namageoff.actnowaz.features.main

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.namageoff.actnowaz.R
import java.util.*

class AlarmBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.i("Alarm", "Alarm Triggered")
        Toast.makeText(context, "Alarm Triggered", Toast.LENGTH_SHORT).show()

        val dahuaPushActivity = Intent(context, MainActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, dahuaPushActivity, PendingIntent.FLAG_ONE_SHOT)

        val channelId = createNotificationChannel(context)

        val notificationBuilder = channelId?.let {
            NotificationCompat.Builder(context, it)
                    .setContentTitle("Get involved!")
                    .setContentText("Weekly activism:")
                    .setStyle(NotificationCompat.BigTextStyle().bigText("Check out this week's activity!"))
                    /*.setLargeIcon(largeIcon)*/
                    .setSmallIcon(R.drawable.ic_launcher_web) //needs white icon with transparent BG (For all platforms)
                    .setColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
                    .setVibrate(longArrayOf(1000, 1000))
                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                    .setContentIntent(pendingIntent)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setAutoCancel(true)
        }

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify((Date(System.currentTimeMillis()).time / 1000L % Integer.MAX_VALUE).toInt() /* ID of notification */, notificationBuilder?.build())


    }

    private fun createNotificationChannel(context: Context): String? {
        // NotificationChannels are required for Notifications on O (API 26) and above.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "Channel_id"
            val channelName = "Application_name"
            val channelDescription = "Application_name Alert"
            val channelImportance = NotificationManager.IMPORTANCE_DEFAULT
            val channelEnableVibrate = true

            val notificationChannel = NotificationChannel(channelId, channelName, channelImportance)
            notificationChannel.description = channelDescription
            notificationChannel.enableVibration(channelEnableVibrate)
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)

            return channelId
        } else {
            // Returns null for pre-O (26) devices.
            return null
        }
    }
}