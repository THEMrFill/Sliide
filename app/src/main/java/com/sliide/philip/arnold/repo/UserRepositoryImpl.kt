package com.sliide.philip.arnold.repo

import android.annotation.SuppressLint
import com.sliide.philip.arnold.api.ApiNetwork
import com.sliide.philip.arnold.interfaces.UserResponseInterface
import com.sliide.philip.arnold.interfaces.UsersResponseInterface
import com.sliide.philip.arnold.model.users.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepositoryImpl(private val service: ApiNetwork): UserRepository {
    @SuppressLint("CheckResult")
    override fun getUsers(response: UsersResponseInterface) {
        service.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ results ->
                response.ReturnUsers(results)
            }, response::ReturnError)
    }

    @SuppressLint("CheckResult")
    override fun addUser(user: User, response: UserResponseInterface) {
        service.makeUser(user)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ user ->
                response.ReturnUser(user)
            }, response::ReturnError)
    }
}