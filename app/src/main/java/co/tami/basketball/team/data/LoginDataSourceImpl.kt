package co.tami.basketball.team.data

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class LoginDataSourceImpl(
    private val firebaseAuth: FirebaseAuth
) : LoginDataSource {
    override fun login(
        email: String,
        password: String
    ): Flow<UserData> = flow {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult: AuthResult ->
                Timber.i("FirebaseAuth Login Success : ${authResult.user?.uid}")

            }.addOnFailureListener { exception: Exception ->
                Timber.e("loginError : ${exception.message}")

            }
    }
}