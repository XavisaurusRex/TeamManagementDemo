package cat.devsofthecoast.teammanagementdemo.commons.services.players

import cat.devsofthecoast.teammanagementdemo.commons.models.users.Player
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

interface PlayersService {
    fun setPlayers(players: List<Player>, listener: ServiceCallback<Boolean>?)
}