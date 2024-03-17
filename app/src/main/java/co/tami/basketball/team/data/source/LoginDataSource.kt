package co.tami.basketball.team.data.source

import co.tami.basketball.team.data.UserData

interface LoginDataSource {
    suspend fun login(
        email: String,
        password: String
    ): UserData
}