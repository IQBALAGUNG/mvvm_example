package com.iqbal.mvvmexample.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iqbal.mvvmexample.data.db.dao.UserInfoDao
import com.iqbal.mvvmexample.data.model.NetworkStatus
import com.iqbal.mvvmexample.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val userInfoDao: UserInfoDao,
    private val apiService: ApiService
) : ViewModel() {

    private val _loginRequest = MutableLiveData<NetworkStatus>()
    val loginRequest: LiveData<NetworkStatus> = _loginRequest

    suspend fun loginUser(userName: String, password: String) {
        _loginRequest.postValue(NetworkStatus.LOADING)
        try {
            withContext(Dispatchers.IO) {
                val requestLogin = apiService.loginUser(userName, password).await()
                requestLogin.status.let { success ->
                    if (success) {
                        userInfoDao.upSert(requestLogin.data)
                        _loginRequest.postValue(NetworkStatus.SUCCESS)
                    } else {
                        _loginRequest.postValue(NetworkStatus.ERROR)
                    }
                }
            }
        } catch (e: Exception) {
            _loginRequest.postValue(NetworkStatus.EXCEPTION)
            e.printStackTrace()
        }
    }

}