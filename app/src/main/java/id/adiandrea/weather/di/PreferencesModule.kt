package id.adiandrea.weather.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "location")

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Provides
    fun providesDataStore(@ApplicationContext applicationContext: Context): DataStore<Preferences> {
        return applicationContext.dataStore
    }

}