package com.example.testewebsocket.Socket

import android.util.Log
import com.example.testewebsocket.Util.MyLog
import com.example.testewebsocket.Util.Utils
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.json.JSONObject

class SocketClient (private val listener: EventHandle): WebSocketListener(){

    private val log =
        MyLog(SocketClient::class.java.simpleName)
    private val notification = "notification"

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        Log.i("onOpen", "response: " + response.message())
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        Log.i("onMessage", "text: " + text)

        if(text != ""){
            val data = JSONObject(text)
            val hasData = data.has("data")
            val hasEv = data.has("ev")

            log.showD("onMessage", "hasChannel", hasData)
            log.showD("onMessage", "hasEv", hasEv)

            if(hasEv){
                val ev = data.getString("ev")

                if(ev == notification && hasData) {
                    listener.onEvent(Utils.jsonToEventObject(data))
                }
            }
        }
        else
            log.showD("onMessage", "text", "vazio")
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        log.showD("onClosed", "reason", reason)
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
        log.showD("onClosing", "reason", reason)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        log.showD("onFailure", "throwable", t.message)

        listener.onSocketFail()
    }

}