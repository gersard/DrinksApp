package cl.gerardomascayano.drinksapp.di

import cl.gerardomascayano.drinksapp.framework.data.DrinkMapper
import cl.gerardomascayano.drinksapp.framework.db.DrinksAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object DrinksModule {

    @Provides
    fun providesDrinksDao(db: DrinksAppDatabase) = db.drinksDao()

    @Provides
    fun providesDrinkMapper() = DrinkMapper()

}