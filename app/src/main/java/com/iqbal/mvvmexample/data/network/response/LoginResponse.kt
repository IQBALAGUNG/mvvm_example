package com.iqbal.mvvmexample.data.network.response


import com.iqbal.mvvmexample.data.db.entity.UserInfo

data class LoginResponse(
    val status: Boolean,
    val code: Int,
    val data: UserInfo
)