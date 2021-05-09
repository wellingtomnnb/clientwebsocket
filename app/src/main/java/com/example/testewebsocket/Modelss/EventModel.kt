

package com.example.testewebsocket.Modelss

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EventModel {
    @SerializedName("ev")
    @Expose
    var ev: String? = null
    @SerializedName("data")
    @Expose
    var data: Data? = null


    class Data{
        @SerializedName("hash_id")
        @Expose
        var hash_id: String? = null

        @SerializedName("channel")
        @Expose
        var channel: String? = null

        @SerializedName("type")
        @Expose
        var type: String? = null

        @SerializedName("message")
        @Expose
        var message: String? = null
    }

}