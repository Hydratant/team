package co.tami.basketball.team.data.source

import co.tami.basketball.team.data.PlayerData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FirebasePlayerDataSourceImpl(
    private val db: FirebaseFirestore
) : PlayerDataSource {

    override suspend fun getPlayers(): List<PlayerData> =
        suspendCancellableCoroutine { continuation ->
            db.collection(KEY_TEAM_COLLECTION_NAME)
                .get()
                .addOnSuccessListener { result ->
                    val players = result.toObjects(PlayerData::class.java)
                    continuation.resume(players)
                }.addOnFailureListener { exception ->
                    continuation.resumeWithException(exception)
                }
        }

    override suspend fun getPlayer(id: Long): PlayerData {
        TODO("Not yet implemented")
    }

    companion object {
        private const val KEY_TEAM_COLLECTION_NAME = "유리수"
    }

}