package co.tami.basketball.team.di

import co.tami.basketball.team.data.LoginDataSource
import co.tami.basketball.team.data.LoginDataSourceImpl
import co.tami.basketball.team.data.repo.LoginRepository
import co.tami.basketball.team.data.repo.LoginRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Singleton
    @Provides
    fun provideLoginDataSource(firebaseAuth: FirebaseAuth): LoginDataSource =
        LoginDataSourceImpl(firebaseAuth)

    @Singleton
    @Provides
    fun provideLoginRepository(loginDataSource: LoginDataSource): LoginRepository =
        LoginRepositoryImpl(loginDataSource)

}