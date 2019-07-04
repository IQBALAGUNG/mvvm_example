package com.iqbal.mvvmexample.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iqbal.mvvmexample.data.db.dao.UserInfoDao
import com.iqbal.mvvmexample.data.network.ApiService

class LoginViewModelFactory(
    private var userInfoDao: UserInfoDao,
    private var apiService: ApiService
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(userInfoDao, apiService) as T
    }

}