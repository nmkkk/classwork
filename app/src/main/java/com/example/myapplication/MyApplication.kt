package com.example.myapplication

import android.app.Application

class MyApplication: Application() {
    val database: ItemRoomDataBase by lazy {
        ItemRoomDataBase.getDatabase(this)
    }
}