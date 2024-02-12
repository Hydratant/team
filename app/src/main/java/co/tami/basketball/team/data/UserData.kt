package co.tami.basketball.team.data

import com.google.firebase.auth.FirebaseUser

data class UserData(
    val uid: String,
    val nickName: String,
    val email: String
)

fun FirebaseUser.toUserData(): UserData = UserData(
    uid,
    displayName ?: "",
    email ?: ""
)