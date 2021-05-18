package com.example.testewebsocket.Socket

import com.example.testewebsocket.Modelss.EventModel
import com.example.testewebsocket.Modelss.NotifyModel
import kotlinx.coroutines.Deferred

interface EventHandle {
    fun onEvent(event: EventModel)
    fun onSocketFail()
    //fun onNotification(notify: NotifyModel)

}