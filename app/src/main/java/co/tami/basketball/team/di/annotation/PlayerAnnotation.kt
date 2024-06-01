package co.tami.basketball.team.di.annotation

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FakePlayerDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FirebasePlayerDataSource
