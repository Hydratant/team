package co.tami.basketball.team.data.repo

import co.tami.basketball.team.data.LoginDataSource
import co.tami.basketball.team.data.UserData

class LoginRepositoryImpl(
    private val loginDataSource: LoginDataSource
) : LoginRepository {
    override suspend fun login(email: String, password: String): UserData =
        loginDataSource.login(email, password)
}