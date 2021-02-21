package com.sliide.philip.arnold.api

import com.sliide.philip.arnold.BuildConfig
import com.sliide.philip.arnold.model.Empty
import com.sliide.philip.arnold.model.users.User
import com.sliide.philip.arnold.model.users.Users
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiNetwork {
    @GET("users")
    fun getUsers() : Flowable<Users>

    @POST("users")
    fun makeUser(
            @Body user: User,
            @Header("Authorization") auth: String = "Bearer ${BuildConfig.API_TOKEN}",
    ) : Flowable<User>
}