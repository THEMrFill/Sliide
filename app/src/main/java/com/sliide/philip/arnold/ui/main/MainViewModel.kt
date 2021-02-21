package com.sliide.philip.arnold.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sliide.philip.arnold.interfaces.UsersResponseInterface
import com.sliide.philip.arnold.model.users.Users
import com.sliide.philip.arnold.repo.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class MainViewModel(val repo: UserRepository)
    : ViewModel(), CoroutineScope, UsersResponseInterface {
    // Coroutine's background job
    private val job = Job()

    // Define default thread for Coroutine as Main and add job
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val users = MutableLiveData<Users>()

    init {
        getUsers()
    }

    fun getUsers() {
        repo.getUsers(this)
    }

    override fun ReturnUsers(newUsers: Users) {
        users.value = newUsers
    }

    override fun ReturnError(error: Throwable) {
        // do nothing at the moment
    }

}