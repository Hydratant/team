package co.tami.basketball.team.data

import kotlinx.coroutines.flow.Flow

interface LoginDataSource {
    fun login(
        email: String,
        password: String
    ): Flow<UserData>
}