package com.sliide.philip.arnold.interfaces

import com.sliide.philip.arnold.model.users.User
import com.sliide.philip.arnold.model.users.Users

interface UsersResponseInterface {
    fun ReturnUsers(newUsers: Users)
    fun ReturnError(error: Throwable)
}

interface UserResponseInterface {
    fun ReturnUser(newUser: User)
    fun ReturnError(error: Throwable)
}