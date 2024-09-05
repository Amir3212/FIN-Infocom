package com.amir.fininfocom.utils.di


import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.amir.fininfocom.utils.redux.ApplicationState
import com.amir.fininfocom.utils.redux.Store
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object BaseAppModule {

    @Provides
    @Singleton
    fun provideSharedPrefs(applicationState: Application): SharedPreferences =
        applicationState.getSharedPreferences("com.utils", Context.MODE_PRIVATE)


    @Provides
    @Singleton
    fun provideStore(): Store<ApplicationState> = Store(ApplicationState())


}
