package cl.gerardomascayano.drinksapp.di

import android.content.Context
import androidx.room.Room
import cl.gerardomascayano.drinksapp.data.db.DrinksAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRoomInstance(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DrinksAppDatabase::class.java, DrinksAppDatabase.DATABASE_NAME).build()

}