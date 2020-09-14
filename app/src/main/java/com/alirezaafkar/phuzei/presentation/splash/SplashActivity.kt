package com.seiyria.reuuzei.presentation.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.seiyria.reuuzei.App
import com.seiyria.reuuzei.presentation.login.LoginActivity
import com.seiyria.reuuzei.presentation.main.MainActivity
import javax.inject.Inject

/**
 * Created by Alireza Afkar on 16/9/2018AD.
 */
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SplashViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.get(this).component?.inject(this)
        super.onCreate(savedInstanceState)

        viewModel.loginActivityObservable.observe(this) {
            LoginActivity.start(this)
            finish()
        }

        viewModel.mainActivityObservable.observe(this) {
            MainActivity.start(this)
            finish()
        }

        viewModel.subscribe()
    }
}
