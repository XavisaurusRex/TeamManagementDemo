package cat.devsofthecoast.teammanagementdemo.commons.services.players.impl

import cat.devsofthecoast.teammanagementdemo.commons.exceptions.PostingFirebaseException
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Player
import cat.devsofthecoast.teammanagementdemo.commons.services.BaseService
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback
import cat.devsofthecoast.teammanagementdemo.commons.services.players.PlayersService
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DatabaseReference

class PlayersServiceImpl: BaseService(), PlayersService {
    private val PLAYERS_LOCATION = "players"
    override val refTable: DatabaseReference = firebaseDatabase.child(PLAYERS_LOCATION)

    override fun setPlayers(players: List<Player>, listener: ServiceCallback<Boolean>?) {
        val keysPlayers = mutableMapOf<String, Player>()
        for (player: Player in players) {
            if(player.key == null) assignNewKey(player)
            keysPlayers[player.key!!] = player
        }
        if (keysPlayers.isNotEmpty()) {
            addNewDataList(keysPlayers, OnCompleteListener {
                when {
                    it.isSuccessful -> {
                        listener?.onSuccess(it.isSuccessful)
                    }
                    it.isCanceled -> listener?.onError(PostingFirebaseException())
                    else -> listener?.onError(PostingFirebaseException())
                }
            })
        } else {
            listener?.onError(PostingFirebaseException())
        }
    }
}