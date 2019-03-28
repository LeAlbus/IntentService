package com.intent.pedrolopes.intentservice

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.os.SystemClock
import android.text.format.DateFormat
import org.greenrobot.eventbus.EventBus

class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {

        EventBus.getDefault().post(Status.LOADING)

        val msg = intent?.getStringExtra(PARAM_IN)
        SystemClock.sleep(3000)

        val result = "$msg ${DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis())}"

        val intent = Intent()
        intent.action = ACTION
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.putExtra(PARAM_OUT, result)
        sendBroadcast(intent)

        EventBus.getDefault().post(Status.SUCCESS)


    }

    companion object {
        val PARAM_IN = "input"
        val PARAM_OUT = "output"
        val ACTION = "br.com.pedrolopes.intent.action.RESPONSE"
    }
}
