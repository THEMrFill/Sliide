package com.sliide.philip.arnold.repo

import com.sliide.philip.arnold.interfaces.UserResponseInterface
import com.sliide.philip.arnold.interfaces.UsersResponseInterface
import com.sliide.philip.arnold.model.users.User

interface UserRepository {
    fun getUsers(response: UsersResponseInterface)
    fun addUser(user: User, response: UserResponseInterface)
}