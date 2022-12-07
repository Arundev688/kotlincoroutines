package com.hutech.coroutines.apilocalcachi

import retrofit2.http.GET

interface BankApi {

    companion object{
        const val BASE_URL = "https://random-data-api.com/api/v2/"
    }

    @GET("banks?size=20")
    suspend fun getBanks(): List<bank>

}