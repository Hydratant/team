package co.tami.basketball.team.data.repo

import co.tami.basketball.team.data.UserData

interface LoginRepository {
    suspend fun login(email: String, password: String): UserData
}