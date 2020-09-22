package cl.gerardomascayano.drinksapp.di

import cl.gerardomascayano.drinksapp.domain.DrinkUseCase
import cl.gerardomascayano.drinksapp.domain.DrinkUseCaseImpl
import cl.gerardomascayano.drinksapp.data.DrinkDataSource
import cl.gerardomascayano.drinksapp.data.LocalDrinkDataSourceImpl
import cl.gerardomascayano.drinksapp.data.repository.DrinkRepository
import cl.gerardomascayano.drinksapp.data.repository.DrinkRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class DrinkFragmentModule {

    @Binds
    abstract fun bindsUseCase(useCase: DrinkUseCaseImpl): DrinkUseCase

    @Binds
    abstract fun bindsRepo(repository: DrinkRepositoryImpl): DrinkRepository

    @Binds
    abstract fun bindsLocalDataSource(localDataSource: LocalDrinkDataSourceImpl): DrinkDataSource
}