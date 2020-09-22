package cl.gerardomascayano.drinksapp.di

import cl.gerardomascayano.drinksapp.data.DrinkMapper
import cl.gerardomascayano.drinksapp.data.db.DrinksAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object DrinksModule {

    @Provides
    fun providesDrinksDao(db: DrinksAppDatabase) = db.drinksDao()

    @Provides
    fun providesDrinkMapper() = DrinkMapper()

}