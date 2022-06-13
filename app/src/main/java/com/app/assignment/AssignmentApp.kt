package com.app.assignment

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import io.paperdb.Paper

@HiltAndroidApp
class AssignmentApp: Application()  {
    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
        FirebaseApp.initializeApp(this)
    }
}