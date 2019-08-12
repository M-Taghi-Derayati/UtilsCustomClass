package com.mtd.utilscustomclass.SocketNotification

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ServerRegisterSocketModel(
    @SerializedName("topic")
    @Expose
    var topic:String?,
    @SerializedName("token")
    @Expose
    var token:String?
)