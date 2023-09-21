package com.example.navigationcomponent.notification

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.navigationcomponent.MainActivity
import com.example.navigationcomponent.R
import com.example.navigationcomponent.notification.AlarmNotification.Companion.NOTIFICATION_ID
import java.util.Calendar

class MainActivityNotification : AppCompatActivity() {

    companion object {
        const val MY_CHANNEL_ID = "myChannel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_notification)
        val myNotificationButton = findViewById<Button>(R.id.btnNotification)

        //TODO a partir de cierta ersion se nececita crear un canal para la notification
        createChannel()
        myNotificationButton.setOnClickListener {

//            Alarm SimpleNotification
            //createSimpleNotification()


            /*PROGRAMTE NOTIFICATION*/
            scheduleNotification()
        }
    }

    private fun scheduleNotification() {
        val intent = Intent(applicationContext, AlarmNotification::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            Calendar.getInstance().timeInMillis + 15000,
            pendingIntent
        )
    }

    fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                MY_CHANNEL_ID,
                "MySuperChannel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "SUSCRIBETE"
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createSimpleNotification() {

//        Crear una sola intancia al momento de abrir la notificacion PODEMOS MANDAR A LLAMAR CUALQUIERACTIVIDAD
        val intent = Intent(this, MainActivityNotification::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        //En la  version a cero o la bandera
        val flag =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, flag)

        var builder = NotificationCompat.Builder(this, MY_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_delete)
            .setContentTitle("My title")
            .setContentText("Esto es un ejemplo <3")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Holita holi Holita holi Holita holi Holita holi Holita holi Holita holi Holita holi Holita holi Holita holi Holita holi Holita holi Holita holi Holita holi ")
            )
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(1, builder.build())
        }
    }
}