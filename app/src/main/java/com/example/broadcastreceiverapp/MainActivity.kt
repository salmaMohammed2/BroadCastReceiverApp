package com.example.broadcastreceiverapp

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    val br = MyBroadCastReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var filter =IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            /*if(intent?.action.equals("android.intent.action.ACTION_POWER_CONNECTED"))
                Toast.makeText(this@MainActivity, "the charger connected", Toast.LENGTH_SHORT).show()
            else if(intent?.action.equals("android.intent.action.ACTION_POWER_DISCONNECTED"))
                Toast.makeText(this@MainActivity, "the charger disconnected", Toast.LENGTH_SHORT).show()
*/
        }
        registerReceiver(br,filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(br)
    }




}

