package cat.devsofthecoast.teammanagementdemo.commons.services.teams

import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Player
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

interface TeamsService {
    fun setTeams(teams: List<Team>, listener: ServiceCallback<Boolean>?)
    fun getTeams(serviceCallback: ServiceCallback<ArrayList<Team>>)
}