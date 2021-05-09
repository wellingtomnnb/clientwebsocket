package com.example.testewebsocket.Socket

import com.example.testewebsocket.Modelss.EventModel
import com.example.testewebsocket.Modelss.NotifyModel

interface EventHandle {
    fun onEvent(data: EventModel)
    fun onSocketFail()
    fun onNotification(notify: NotifyModel)

}