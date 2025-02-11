package com.hutech.coroutines.locationinbackground

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.LocationServices
import com.hutech.coroutines.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LocationService: Service() {

    private val serviceScope = CoroutineScope(SupervisorJob()+Dispatchers.IO)
    private lateinit var locationClint: LocationClint

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        locationClint = DefaultLocationClint(
            applicationContext,
            LocationServices.getFusedLocationProviderClient(applicationContext)
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action){
            ACTION_START -> start()
            ACTION_STOP -> stop()
        }

        return super.onStartCommand(intent, flags, startId)

    }

    private fun start(){
       val notification = NotificationCompat.Builder(this,"location")
           .setContentTitle("Tracking Location")
           .setContentText("Location : null")
           .setSmallIcon(R.drawable.ic_cancel)
           .setOngoing(true)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        locationClint
            .getLocationUpdates(10000L)
            .catch { e->e.printStackTrace() }
            .onEach { location ->  
                val lat = location.latitude.toString().takeLast(3)
                val long = location.longitude.toString().takeLast(3)
                val updateNotification = notification.setContentText(
                    "Location: ($lat,$long)"
                )
                notificationManager.notify(1,updateNotification.build())
            }
            .launchIn(serviceScope)

        startForeground(1,notification.build())
    }

    private fun stop(){
        stopForeground(true)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    companion object{
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
    }



}