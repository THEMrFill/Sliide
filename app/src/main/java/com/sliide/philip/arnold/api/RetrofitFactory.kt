package com.sliide.philip.arnold.api

import android.annotation.SuppressLint
import com.sliide.philip.arnold.BuildConfig
import com.sliide.philip.arnold.interfaces.UserResponseInterface
import com.sliide.philip.arnold.interfaces.UsersResponseInterface
import com.sliide.philip.arnold.model.users.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object RetrofitFactory {
    private const val repoBaseUrl = BuildConfig.API_URL
    private val repoRetrofit by lazy {
        Retrofit.Builder()
                .baseUrl(repoBaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private val service by lazy {
        repoRetrofit.create(ApiNetwork::class.java)
    }

    @SuppressLint("CheckResult")
    @Throws(IOException::class)
    fun getUsers(responseHandler: UsersResponseInterface) {
        service.getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ results ->
                    responseHandler.ReturnUsers(results)
                }, responseHandler::ReturnError)
    }

    @SuppressLint("CheckResult")
    @Throws(IOException::class)
    fun makeUser(user: User, responseHandler: UserResponseInterface) {
        service.makeUser(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    responseHandler.ReturnUser(result)
                }, responseHandler::ReturnError)
    }
}