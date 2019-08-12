package com.mtd.sampleutilsclass

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mtd.utilscustomclass.SocketNotification.SocketManagment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val socketManagment = SocketManagment()
        socketManagment.setConnect()


    }
}
