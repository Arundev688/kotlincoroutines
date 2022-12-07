package com.hutech.coroutines.network


import com.hutech.coroutines.model.PostModel
import com.hutech.coroutines.model.User

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("posts")
    fun fetchAllPosts(): Call<List<PostModel>>

    @POST("users")
    fun createPost(@Body user:User) : Call<List<User>>

}