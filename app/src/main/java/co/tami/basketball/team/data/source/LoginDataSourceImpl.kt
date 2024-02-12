package co.tami.basketball.team.data.source

import co.tami.basketball.team.data.UserData
import co.tami.basketball.team.data.toUserData
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class LoginDataSourceImpl(
    private val firebaseAuth: FirebaseAuth
) : LoginDataSource {
    override suspend fun login(
        email: String,
        password: String
    ): UserData = suspendCancellableCoroutine { continuation: CancellableContinuation<UserData> ->
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult: AuthResult ->
                authResult.user?.let { firebaseUser: FirebaseUser ->
                    Timber.i("FirebaseAuth Login Success : ${firebaseUser.uid}")
                    continuation.resume(firebaseUser.toUserData())
                } ?: continuation.resumeWithException(Exception("login User is Null"))

            }.addOnFailureListener { exception: Exception ->
                Timber.e("loginError : ${exception.message}")
                continuation.resumeWithException(exception)
            }
    }
}