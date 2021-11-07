package com.example.broadcastreceiverapp

import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import android.net.NetworkInfo

import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService


class MyBroadCastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if(isOnline(context!!)==true)
            Toast.makeText(context, "the internet connected", Toast.LENGTH_LONG).show()
        else if(isOnline(context!!)==false)
            Toast.makeText(context, "the internet disconnected", Toast.LENGTH_LONG).show()

  /*      if (intent?.action.equals(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION)) {
            if (intent?.getBooleanExtra(WifiManager.EXTRA_SUPPLICANT_CONNECTED, false) == true) {
                Toast.makeText(context, "the charger connected", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "the charger disconnected", Toast.LENGTH_SHORT).show()
            }
        }*/

/*        val pendingResult: PendingResult = goAsync()
        val asyncTask = Task(pendingResult, intent,context)
        asyncTask.execute()*/
    }

    fun isOnline(context: Context?): Boolean {
        val connMgr = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
    // to avoid killing in system process so work on another thread
    private class Task(
        private val pendingResult: PendingResult,
        private val intent: Intent?,
        private val context: Context?) : AsyncTask<String, Int, String>(){

        override fun doInBackground(vararg params: String?): String {
            val sb = StringBuilder()
            sb.append("Action: ${intent?.action}\n")
            sb.append("URI: ${intent?.toUri(Intent.URI_INTENT_SCHEME)}\n")
            return toString().also { log ->
                Log.d(TAG, log)
                if(intent?.action.equals("android.intent.action.ACTION_POWER_CONNECTED"))
                    Toast.makeText(context, "the charger connected", Toast.LENGTH_SHORT).show()
                else if(intent?.action.equals("android.intent.action.ACTION_POWER_DISCONNECTED"))
                    Toast.makeText(context, "the charger disconnected", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pendingResult.finish()
        }
    }
}