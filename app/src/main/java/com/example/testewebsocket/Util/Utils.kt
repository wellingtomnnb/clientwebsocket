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

        fun jsonToChannelList(array: JSONArray): List<EventModel>{
            val gson = Gson()
            val tamanho = array.length()
            val data = mutableListOf<EventModel>()

            for(i in 0 until tamanho){
                val json = array.getJSONObject(i)
                val channel = gson.fromJson(json.toString(), EventModel::class.java)

                data.add(channel)
            }

            return data.sortedWith(compareBy {it.server})
        }

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