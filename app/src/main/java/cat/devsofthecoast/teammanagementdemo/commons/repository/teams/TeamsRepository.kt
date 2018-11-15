package cat.devsofthecoast.teammanagementdemo.commons.repository.teams

import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.repository.base.BaseRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

interface TeamsRepository : BaseRepository {
    fun setTeams(teams: List<Team>, listener: ServiceCallback<Boolean>?)
    fun getTeams(serviceCallback: ServiceCallback<ArrayList<Team>>)
    fun setTeam(input: Team, serviceCallback: ServiceCallback<Void?>)
    fun getTeam(key: String, serviceCallback: ServiceCallback<Team>)
}