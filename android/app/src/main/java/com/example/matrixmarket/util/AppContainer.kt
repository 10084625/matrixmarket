package com.example.matrixmarket.util

import com.example.matrixmarket.data.local.SessionManager
import com.example.matrixmarket.data.remote.RetrofitClient
import com.example.matrixmarket.data.repository.AuthRepository
import com.example.matrixmarket.data.repository.ListingRepository
import com.example.matrixmarket.data.repository.ProfileRepository
import com.example.matrixmarket.data.repository.TradeRepository

// A lightweight manual service locator (avoids pulling in a full DI framework for
// what is a fairly small app). Repositories are simple and cheap, so plain lazy
// singletons here keep things easy to read, test, and mock.
object AppContainer {
    // Initialize RetrofitClient with the session manager
    val sessionManager: SessionManager get() = RetrofitClient.sessionManager

    val authRepository: AuthRepository by lazy {
        AuthRepository(RetrofitClient.apiService, RetrofitClient.sessionManager)
    }
    val listingRepository: ListingRepository by lazy {
        ListingRepository(RetrofitClient.apiService)
    }
    val tradeRepository: TradeRepository by lazy {
        TradeRepository(RetrofitClient.apiService)
    }
    val profileRepository: ProfileRepository by lazy {
        ProfileRepository(RetrofitClient.apiService)
    }
}