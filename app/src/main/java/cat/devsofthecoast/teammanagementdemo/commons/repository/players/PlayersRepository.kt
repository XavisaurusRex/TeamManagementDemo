package cat.devsofthecoast.teammanagementdemo.commons.repository.players

import cat.devsofthecoast.teammanagementdemo.commons.models.users.Player
import cat.devsofthecoast.teammanagementdemo.commons.repository.base.BaseRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

interface PlayersRepository: BaseRepository {
    fun setPlayers(players: List<Player>, listener: ServiceCallback<Boolean>?)
}