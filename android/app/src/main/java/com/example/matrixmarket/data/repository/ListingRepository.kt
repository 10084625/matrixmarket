package com.example.matrixmarket.data.repository

import android.util.Log
import com.example.matrixmarket.data.remote.ApiService
import com.example.matrixmarket.data.remote.dto.CategoryDto
import com.example.matrixmarket.data.remote.dto.CreateListingRequest
import com.example.matrixmarket.data.remote.dto.ListingResponse
import com.example.matrixmarket.util.Resource

private const val TAG = "ListingRepository"
// Repository for fetching listings from the API
class ListingRepository(private val api: ApiService) {
    suspend fun getListings(categoryId: Int? = null, search: String? = null): Resource<List<ListingResponse>> {
        return try {
            val response = api.getListings(categoryId, search)
            if (response.isSuccessful) {
                Resource.Success(response.body() ?: emptyList())
            } else {
                Resource.Error("Could not load listings (${response.code()})")
            }
        } catch (e: Exception) {
            Log.e(TAG, "getListings failed", e)
            Resource.Error(e.localizedMessage ?: "Network error while loading listings.")
        }
    }
    // Fetch categories from the API
    suspend fun getCategories(): Resource<List<CategoryDto>> {
        return try {
            val response = api.getCategories()
            if (response.isSuccessful) Resource.Success(response.body() ?: emptyList())
            else Resource.Error("Could not load categories (${response.code()})")
        } catch (e: Exception) {
            Log.e(TAG, "getCategories failed", e)
            Resource.Error(e.localizedMessage ?: "Network error while loading categories.")
        }
    }
    // Fetch a single listing by its ID from the API
    suspend fun getListingById(id: Int): Resource<ListingResponse> {
        return try {
            val response = api.getListingById(id) // Assuming getListingById is a suspend function
            if (response.isSuccessful && response.body() != null) Resource.Success(response.body()!!)
            else Resource.Error("Could not load this listing (${response.code()})")
        } catch (e: Exception) {
            Log.e(TAG, "getListingById failed", e)
            Resource.Error(e.localizedMessage ?: "Network error while loading this listing.")
        }
    }

    suspend fun createListing(request: CreateListingRequest): Resource<ListingResponse> {
        return try {
            val response = api.createListing(request)
            if (response.isSuccessful && response.body() != null) {
                Log.i(TAG, "Listing created: ${response.body()!!.listingId}") // Log the listing ID
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.errorBody()?.string() ?: "Could not create listing (${response.code()})")
            }
        } catch (e: Exception) {
            Log.e(TAG, "createListing failed", e)
            Resource.Error(e.localizedMessage ?: "Network error while creating listing.")
        }
    }





}