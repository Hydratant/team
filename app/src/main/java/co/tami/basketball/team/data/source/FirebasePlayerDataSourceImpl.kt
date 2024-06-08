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

    override suspend fun getPlayer(id: String): PlayerData =
        suspendCancellableCoroutine { continuation ->
            db.collection(KEY_TEAM_COLLECTION_NAME).document(id)
                .get()
                .addOnSuccessListener { result ->
                    val player: PlayerData? = result.toObject(PlayerData::class.java)

                    // Player를 찾이 못 할 경우 예외를 발생시킨다.
                    if (player == null) {
                        continuation.resumeWithException(IllegalArgumentException("Player not found"))
                        return@addOnSuccessListener
                    }
                    // Player Resume
                    continuation.resume(player)
                }.addOnFailureListener { exception ->
                    continuation.resumeWithException(exception)
                }
        }

    companion object {
        private const val KEY_TEAM_COLLECTION_NAME = "유리수"
    }

}