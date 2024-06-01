package co.tami.basketball.team.data.source

import co.tami.basketball.team.data.PlayerData
import com.google.firebase.firestore.FirebaseFirestore

class FirebasePlayerDataSourceImpl(
    private val db: FirebaseFirestore
) : PlayerDataSource {
    override suspend fun getPlayers(): List<PlayerData> {
        TODO("Not yet implemented")
    }

    override suspend fun getPlayer(id: Long): PlayerData {
        TODO("Not yet implemented")
    }
}