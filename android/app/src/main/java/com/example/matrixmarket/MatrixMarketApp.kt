package com.example.matrixmarket

import android.app.Application
import android.util.Log
import com.example.matrixmarket.data.local.SessionManager
import com.example.matrixmarket.data.remote.RetrofitClient

// Custom Application class for the Matrix Market app.
class MatrixMarketApp : Application() {

    lateinit var sessionManager: SessionManager // Initialize sessionManager here
        private set
    // Called when the application is starting, before any activity, service, or receiver objects (excluding content providers) have been created.
    override fun onCreate() {
        super.onCreate()
        Log.i("MatrixMarketApp", "Application starting up")
        // Initialize RetrofitClient with the application context
        sessionManager = SessionManager(applicationContext)
        RetrofitClient.sessionManager = sessionManager
    }
}