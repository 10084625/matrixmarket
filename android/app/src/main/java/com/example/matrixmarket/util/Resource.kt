package com.example.matrixmarket.util

// Simple sealed wrapper so every screen can render Loading / Success / Error states
// consistently for every network call, keeping the UI robust against failures
sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
    object Idle : Resource<Nothing>()
}
