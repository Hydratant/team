package co.tami.basketball.team.di

import co.tami.basketball.team.data.repo.PlayerRepository
import co.tami.basketball.team.data.repo.PlayerRepositoryImpl
import co.tami.basketball.team.data.source.PlayerDataSource
import co.tami.basketball.team.data.source.fake.FakePlayerDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlayerModule {

    @Singleton
    @Provides
    fun provideFakePlayerDataSource(): PlayerDataSource =
        FakePlayerDataSourceImpl()

    @Singleton
    @Provides
    fun providePlayerRepository(playerDataSource: PlayerDataSource): PlayerRepository =
        PlayerRepositoryImpl(playerDataSource)
}