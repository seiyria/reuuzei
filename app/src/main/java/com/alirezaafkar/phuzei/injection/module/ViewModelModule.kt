package com.seiyria.reuuzei.injection.module

import androidx.lifecycle.ViewModel
import com.seiyria.reuuzei.injection.util.ViewModelKey
import com.seiyria.reuuzei.presentation.album.AlbumViewModel
import com.seiyria.reuuzei.presentation.login.LoginViewModel
import com.seiyria.reuuzei.presentation.setting.SettingsViewModel
import com.seiyria.reuuzei.presentation.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Alireza Afkar on 5/21/20.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AlbumViewModel::class)
    abstract fun bindAlbumViewModel(viewModel: AlbumViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel
}
