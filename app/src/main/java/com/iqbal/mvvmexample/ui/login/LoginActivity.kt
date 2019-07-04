package com.iqbal.mvvmexample.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.iqbal.mvvmexample.R
import com.iqbal.mvvmexample.data.model.NetworkStatus
import com.iqbal.mvvmexample.ui.base.BaseActivity
import com.iqbal.mvvmexample.utils.Sha1Utils
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class LoginActivity : BaseActivity(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: LoginViewModelFactory by instance()

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setOnClickView()

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(LoginViewModel::class.java)

        bindUI()
    }

    private fun bindUI() {
        viewModel.loginRequest.observe(this@LoginActivity, Observer { status ->
            when (status) {
                NetworkStatus.LOADING -> toggleLoading(true)
                NetworkStatus.SUCCESS -> {
                    Toast.makeText(this@LoginActivity, "Login Berhasil", Toast.LENGTH_SHORT).show()
                    toggleLoading(false)
                }
                else -> {
                    Toast.makeText(this@LoginActivity, "Password Salah", Toast.LENGTH_SHORT).show()
                    toggleLoading(false)
                }
            }
        })
    }

    private fun setOnClickView() {
        btn_login.setOnClickListener {
            validateInfo()
        }
    }

    private fun validateInfo() = launch {
        val userName = et_username.text.toString()
        val password = et_password.text.toString()
        when {
            userName.isEmpty() -> et_username.error = getString(R.string.required_value)
            password.isEmpty() -> et_password.error = getString(R.string.required_value)
            else -> {
                viewModel.loginUser(userName, Sha1Utils.getHash(password + "insurance"))
            }
        }
    }

    private fun toggleLoading(isLoading: Boolean) {
        if (isLoading) {
            pb_login_progress.visibility = View.VISIBLE
            btn_login.visibility = View.GONE
        } else {
            pb_login_progress.visibility = View.GONE
            btn_login.visibility = View.VISIBLE
        }
    }
}