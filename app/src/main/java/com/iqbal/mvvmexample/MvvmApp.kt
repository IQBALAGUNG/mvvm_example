package com.iqbal.mvvmexample

import android.app.Application
import com.facebook.stetho.Stetho
import com.iqbal.mvvmexample.data.db.AppDb
import com.iqbal.mvvmexample.data.network.ApiService
import com.iqbal.mvvmexample.ui.login.LoginViewModel
import com.iqbal.mvvmexample.ui.login.LoginViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MvvmApp : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MvvmApp))

        bind() from singleton { AppDb(instance()) }
        bind() from singleton { instance<AppDb>().userInfoDao() }
        bind() from singleton { ApiService() }

        bind() from provider { LoginViewModelFactory(instance(), instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        setupStetho()
    }

    private fun setupStetho() {
        if (BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this)
    }
}