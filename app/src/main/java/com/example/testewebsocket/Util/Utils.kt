package com.example.testewebsocket.Util

import android.os.Build
import com.example.testewebsocket.Modelss.EventModel
import com.example.testewebsocket.Modelss.NotifyModel
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

class Utils {

    companion object {

        fun isMarshmallowOrMore(): Boolean{ return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M }


        fun jsonToEventObject(obj: JSONObject): EventModel{
            val gson = Gson()

            val event = gson.fromJson(obj.toString(), EventModel::class.java)

            return event
        }

        fun jsonToNotify(json: JSONObject): NotifyModel{
            val notify = NotifyModel()

            notify.url = json.getString("file")
            notify.time = json.getInt("timing")

            return notify
        }
    }




}