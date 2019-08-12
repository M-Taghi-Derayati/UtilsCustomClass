package com.mtd.utilscustomclass.SocketNotification

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataNotificationModel(
    @SerializedName("_id")
    @Expose
    var id:String?,
    @SerializedName("text")
    @Expose
    var text:String?,
    @SerializedName("topic")
    @Expose
    var topic:String?,
    @SerializedName("token")
    @Expose
    var token:String?
)