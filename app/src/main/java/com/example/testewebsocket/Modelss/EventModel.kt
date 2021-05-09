

package com.example.testewebsocket.Modelss

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EventModel {
    @SerializedName("actived")
    @Expose
    var actived: Boolean? = null
    @SerializedName("app")
    @Expose
    var app: Boolean? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("number")
    @Expose
    var number: String? = null
    @SerializedName("server")
    @Expose
    var server: Int? = null
    @SerializedName("web")
    @Expose
    var web: Boolean? = null
    @SerializedName("dedicated")
    @Expose
    var isDedicated: Boolean? = null


}