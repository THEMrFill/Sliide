package com.sliide.philip.arnold.model.users

import com.google.gson.annotations.SerializedName

data class Users(
        val code: Int,
        @SerializedName("data")
        val data: List<User>,
        val meta: Meta,
)