package com.seiyria.reuuzei.injection.component

import com.seiyria.reuuzei.injection.module.ContextModule
import com.seiyria.reuuzei.injection.module.DataModule
import com.seiyria.reuuzei.injection.module.NetworkModule
import com.seiyria.reuuzei.injection.module.ViewModelModule
import com.seiyria.reuuzei.injection.util.ViewModelFactoryModule
import com.seiyria.reuuzei.presentation.album.AlbumFragment
import com.seiyria.reuuzei.presentation.login.LoginActivity
import com.seiyria.reuuzei.presentation.main.MainActivity
import com.seiyria.reuuzei.presentation.muzei.PhotosArtProvider
import com.seiyria.reuuzei.presentation.muzei.PhotosWorker
import com.seiyria.reuuzei.presentation.setting.SettingsFragment
import com.seiyria.reuuzei.presentation.splash.SplashActivity
import com.seiyria.reuuzei.util.TokenAuthenticator
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Alireza Afkar on 16/3/2018AD.
 */
@Singleton
@Component(
    modules = [
        DataModule::class,
        ContextModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        ViewModelFactoryModule::class
    ]
)
interface MainComponent {

    fun inject(photosWorker: PhotosWorker)
    fun inject(mainActivity: MainActivity)
    fun inject(loginActivity: LoginActivity)
    fun inject(albumFragment: AlbumFragment)
    fun inject(splashActivity: SplashActivity)
    fun inject(settingsFragment: SettingsFragment)
    fun inject(photosArtProvider: PhotosArtProvider)
    fun inject(tokenAuthenticator: TokenAuthenticator)
}
