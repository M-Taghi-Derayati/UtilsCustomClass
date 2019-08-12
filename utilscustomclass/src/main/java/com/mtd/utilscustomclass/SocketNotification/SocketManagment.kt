package com.mtd.utilscustomclass.SocketNotification

import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject


class SocketManagment {
    val ioOption = IO.Options();
    var socket: Socket
    val gson: Gson = Gson()

    constructor() {
        ioOption.forceNew = true
        ioOption.reconnection = false
        socket = IO.socket("http://192.168.43.59:8000", ioOption)

    }


    fun setConnect() {
        socket.open()

        setJoinServer()
        setJoinRoom()
        setJoinToken()
    }

    fun setJoinServer(){
        val data= ServerRegisterSocketModel("com.mtd.yas.airline","16biHpxTHohnML22phqkng==")
        val sendDataJoinServer = gson.toJson(data)

        socket.on("JoinServer"){
            val dataReceived=gson.fromJson(it[0].toString(), DataNotificationModel::class.java)
            dataReceived.token="16biHpxTHohnML22phqkng=="

            NotificationReceiver(dataReceived)

            val sendData=gson.toJson(dataReceived)
            socket.emit("JoinServerReceived",JSONObject(sendData))
        }

        socket.emit("JoinServer", JSONObject(sendDataJoinServer))
    }


    fun setJoinRoom(){
        socket.on("com.mtd.yas.airline"){
            val dataReceived=gson.fromJson(it[0].toString(), DataNotificationModel::class.java)
            NotificationReceiver(dataReceived)
            val sendData=gson.toJson(dataReceived)
            socket.emit("com.mtd.yas.airlineReceived",JSONObject(sendData))
        }
    }



    fun setJoinToken(){
        socket.on("16biHpxTHohnML22phqkng=="){
            val dataReceived=gson.fromJson(it[0].toString(), DataNotificationModel::class.java)
            NotificationReceiver(dataReceived)
            val sendData=gson.toJson(dataReceived)
            socket.emit("16biHpxTHohnML22phqkng==Received",JSONObject(sendData))
        }
    }


    fun setDisconnect(){
        socket.disconnect()
    }


}