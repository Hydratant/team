package co.tami.basketball.team.di

import co.tami.basketball.team.data.repo.PlayerRepository
import co.tami.basketball.team.data.repo.PlayerRepositoryImpl
import co.tami.basketball.team.data.source.FirebasePlayerDataSourceImpl
import co.tami.basketball.team.data.source.PlayerDataSource
import co.tami.basketball.team.data.source.fake.FakePlayerDataSourceImpl
import co.tami.basketball.team.di.annotation.FakePlayerDataSource
import co.tami.basketball.team.di.annotation.FirebasePlayerDataSource
import com.google.firebase.firestore.FirebaseFirestore
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
    @FirebasePlayerDataSource
    fun provideFirebasePlayerDataSource(
        db: FirebaseFirestore
    ): PlayerDataSource = FirebasePlayerDataSourceImpl(db)

    @Singleton
    @Provides
    @FakePlayerDataSource
    fun provideFakePlayerDataSource(): PlayerDataSource =
        FakePlayerDataSourceImpl()

    @Singleton
    @Provides
    fun providePlayerRepository(
        @FakePlayerDataSource playerDataSource: PlayerDataSource
    ): PlayerRepository =
        PlayerRepositoryImpl(playerDataSource)


}

