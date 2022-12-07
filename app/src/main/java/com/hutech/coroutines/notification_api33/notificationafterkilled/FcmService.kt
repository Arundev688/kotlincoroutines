package com.hutech.coroutines.notification_api33.notificationafterkilled

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.hutech.coroutines.notification_api33.NotificationActivity
import org.json.JSONObject

class FcmService : FirebaseMessagingService() {

    private val TAG = FirebaseMessagingService::class.java.getSimpleName()

    private val pushNotificationAction = "pushNotification"

    override fun onMessageReceived(message: RemoteMessage) {
        try {
            // Check if message contains a notification payload.
            if (message.notification != null) {
                handleNotification(message.notification!!.body.toString())
            }

            // Check if message contains a data payload.
            if (message.data.isNotEmpty()) {
                Log.e(TAG, "Data Payload: " + message.data.toString())

                try {
                    val json = JSONObject(message.data.toString())
                    handleDataMessage(json)
                } catch (e: Exception) {
                    Log.e(TAG, "Exception: " + e.message)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onNewToken(token: String) {
        Log.d("TAG", "Refreshed token: $token")
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
        /*sendRegistrationToServer(token)*/
    }

    private fun handleNotification(message: String) {

        if (!NotificationUtils.isAppInBackground(applicationContext)) {
            // app is in foreground, broadcast the push message
            val pushNotification = Intent(pushNotificationAction)
            pushNotification.putExtra("message", message)
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification)
       /*     // play notification sound
            val notificationUtils = NotificationUtils(applicationContext)
            notificationUtils.playNotificationSound()*/

        }

        val resultIntent = Intent(applicationContext, NotificationActivity::class.java)
        resultIntent.putExtra("message", message)
        NotificationUtils(applicationContext)
            .showNotificationMessage("Safe Home", message)
    }

    private fun handleDataMessage(json: JSONObject) {
        Log.e(TAG, "push json: $json")


        if (!NotificationUtils.isAppIsInBackground(applicationContext)) {
            // app is in foreground, broadcast the push message
            val pushNotification = Intent(Config.PUSH_NOTIFICATION)
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification)
            // play notification sound
            val notificationUtils = NotificationUtils(applicationContext)
//                notificationUtils.playNotificationSound()

        } else {
            // app is in background, show the notification in notification tray
            val pushNotification = Intent(Config.PUSH_NOTIFICATION)
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification)

        }

        val mActivityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val RunningTask = mActivityManager.getRunningTasks(1)
        val ar = RunningTask[0]

    }


}


