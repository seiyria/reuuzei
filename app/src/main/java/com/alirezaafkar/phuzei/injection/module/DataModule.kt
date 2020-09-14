package com.seiyria.reuuzei.injection.module

import android.content.Context
import com.seiyria.reuuzei.data.api.AlbumsApi
import com.seiyria.reuuzei.data.api.PhotosApi
import com.seiyria.reuuzei.data.api.TokenApi
import com.seiyria.reuuzei.data.pref.AppPreferences
import com.seiyria.reuuzei.data.repository.AlbumsRepository
import com.seiyria.reuuzei.data.repository.PhotosRepository
import com.seiyria.reuuzei.data.repository.TokenRepository
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by Alireza Afkar on 17/3/2018AD.
 */
@Module(includes = [ContextModule::class])
class DataModule {

    @Provides
    @Singleton
    fun provideAppPreferences(context: Context): AppPreferences {
        return AppPreferences(context)
    }

    @Provides
    @Singleton
    fun provideTokenRepository(api: TokenApi, prefs: AppPreferences): TokenRepository {
        return TokenRepository(api, prefs)
    }

    @Provides
    @Singleton
    fun provideAlbumsRepository(api: AlbumsApi): AlbumsRepository {
        return AlbumsRepository(api)
    }

    @Provides
    @Singleton
    fun providePhotosRepository(api: PhotosApi): PhotosRepository {
        return PhotosRepository(api)
    }

    @Provides
    fun provideDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}
