package cat.devsofthecoast.teammanagementdemo.commons.repository.players.impl

import cat.devsofthecoast.teammanagementdemo.commons.models.DatabaseModel
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Player
import cat.devsofthecoast.teammanagementdemo.commons.repository.players.PlayersRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback
import cat.devsofthecoast.teammanagementdemo.commons.services.players.impl.PlayersServiceImpl

class PlayersRepositoryImpl : PlayersRepository {

    private val service = PlayersServiceImpl()

    override fun setPlayers(players: List<Player>, listener: ServiceCallback<Boolean>?) {
        service.setPlayers(players, listener)
    }

    override fun assignKey(databaseModel: DatabaseModel) {
        service.assignNewKey(databaseModel)
    }

    override fun assignKeys(databaseModels: List<DatabaseModel>) {
        for (databaseModel in databaseModels){
            assignKey(databaseModel)
        }
    }
}