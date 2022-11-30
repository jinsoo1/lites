package com.theshine.android.lites.util.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import com.theshine.android.lites.R
import com.theshine.android.lites.ui.view.splash.SplashActivity
import java.util.*

class PushMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(msg: RemoteMessage) {
        super.onMessageReceived(msg)
        Log.d("PushMessage", Gson().toJson(msg, RemoteMessage::class.java))
//        Log.d("PushMessageT", msg.data["title"] ?: "")
//        Log.d("PushMessageB", msg.data["body"] ?: "")
//        Log.d("PushMessageTK", msg.data["token"] ?: "")
//        Log.d("PushMessageN", msg.data["name"] ?: "")

            NotificationHelper.push(applicationContext, msg)
    }


}
object NotificationHelper {
    fun push(
        context: Context,
        title: RemoteMessage
//        msg: String,
//        type : String?,
//        token: String?,
//        name : String?
    ) {

//        var requestCode : Int
//
//        when(type){
//            "chat" ->{
//                var tokenCode = token!!.chunked(7)
//                requestCode = tokenCode[tokenCode.lastIndex-1].toInt()
//            }
//            "circle" ->{
//                requestCode = convertStringToHex(token!!)
//                if(requestCode == 2 || requestCode == 3 || requestCode == 4 || requestCode == 5 || requestCode == 6 || requestCode == 100){
//                    requestCode + 100
//                }
//            }
//            "feed" ->{
//                requestCode = 2
//            }
//            "comment" ->{
//                requestCode = 3
//            }
//            "like" ->{
//                requestCode = 4
//            }
//            "follow" ->{
//                requestCode = 5
//            }
//            "made" ->{
//                requestCode = 6
//            }
//            "recomment" ->{
//                requestCode = token!!.toInt()
//            }
//            else ->{
//                requestCode = 100
//            }
//        }

//
        val intent = Intent(context, SplashActivity::class.java)
//        intent.putExtra("type", type)
//        intent.putExtra("token", token)
//        intent.putExtra("name", name)
//        Log.d("PushMessage대기", type!!)
//        Log.d("PushMessage!!!", requestCode.toString())
        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(
                context, 1 /* Request code */, intent,
                PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT

            )
        } else {
            PendingIntent.getActivity(
                context, 1 /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }

        val channelId = context.getString(R.string.app_name)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title.data["title"])
            .setContentText(title.data["body"])
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setSmallIcon(R.drawable.app_icon)
            .setContentIntent(pendingIntent)
            .setColorized(true)
            .setColor(ContextCompat.getColor(context, R.color.green_40C5))

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "LITES",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(1 /* ID of notification */, notificationBuilder.build())
    }

//    private fun convertStringToHex(str: String) : Int {
//        val stringBuilder = StringBuilder()
//        val charArray = str.toCharArray()
//        for (c in charArray) {
//            val charToHex = Integer.toHexString(c.toInt())
//            stringBuilder.append(charToHex)
//        }
//        Log.d("PushMessa","Converted Hex from String: $stringBuilder")
//        var token = stringBuilder.toString().toUpperCase(Locale.getDefault()).chunked(6)
//        var ss = Integer.parseInt(token[token.lastIndex-1], 16)
//        Log.d("PushMessa","Converted Hex from String: $ss")
//        return ss
//    }

}