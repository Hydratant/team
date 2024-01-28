package co.tami.basketball.team.data

interface LoginDataSource {
    suspend fun login(
        email: String,
        password: String
    ): UserData
}