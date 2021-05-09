package com.example.testewebsocket.Modelss

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NotifyModel {

    @SerializedName("file")
    @Expose
    var url: String? = null
    @SerializedName("timing")
    @Expose
    var time: Int = 60
    @SerializedName("type")
    @Expose
    var type: String = "image"


}
