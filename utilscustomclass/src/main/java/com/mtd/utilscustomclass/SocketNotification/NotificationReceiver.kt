package com.mtd.utilscustomclass.SocketNotification

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.blankj.utilcode.util.ActivityUtils
import com.google.gson.Gson
import com.mtd.utilscustomclass.R

import java.util.concurrent.atomic.AtomicInteger

class NotificationReceiver {
    private val ADMIN_CHANNEL_ID = "admin_channel"

    val context = ActivityUtils.getTopActivity()
    val gson: Gson = Gson()

    constructor (remoteMessage: DataNotificationModel) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            onSetNotificationChanel(remoteMessage)

        }
        onNotificationReceiver(remoteMessage)

    }


    private fun onNotificationReceiver(message: DataNotificationModel) {

        val notificationManager = NotificationManagerCompat.from(context)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)


        val intent = Intent(context, context.javaClass)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)


        val notificationBuilder = NotificationCompat.Builder(context, ADMIN_CHANNEL_ID)
            .setContentTitle(message.id)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message.text))
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.notification_icon_background)
            .setContentIntent(pendingIntent)
            .setWhen(System.currentTimeMillis())
            .setSound(defaultSoundUri)

        notificationManager.notify(NotificationID.id, notificationBuilder.build())
    }

    @TargetApi(Build.VERSION_CODES.O)
    fun onSetNotificationChanel(message: DataNotificationModel) {
        val adminChannel = NotificationChannel(ADMIN_CHANNEL_ID, message.id, NotificationManager.IMPORTANCE_LOW)
        adminChannel.description = message.text
        adminChannel.setShowBadge(true)
        adminChannel.enableVibration(true)


        val notificationManager = getSystemService(context, NotificationManager::class.java)!!
        notificationManager.createNotificationChannel(adminChannel)
    }


    private fun onHandelNow() {

    }

    object NotificationID {
        private val c = AtomicInteger(0)

        internal val id: Int
            get() = c.incrementAndGet()
    }
}
