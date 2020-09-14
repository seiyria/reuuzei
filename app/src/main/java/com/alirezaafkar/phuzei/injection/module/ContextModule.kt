package com.seiyria.reuuzei.injection.module

import android.content.Context
import com.seiyria.reuuzei.App
import dagger.Module
import dagger.Provides
import com.seiyria.reuuzei.injection.qualifier.ApplicationContext
import com.seiyria.reuuzei.injection.scope.ApplicationScope

/**
 * Created by Alireza Afkar on 16/3/2018AD.
 */
@Module
class ContextModule(val context: Context) {
    @Provides
    fun provideContext() = context

    @Provides
    @ApplicationScope
    @ApplicationContext
    fun provideApplication(): App = context.applicationContext as App
}
