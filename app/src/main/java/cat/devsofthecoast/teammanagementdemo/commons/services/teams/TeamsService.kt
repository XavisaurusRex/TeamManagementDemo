package cat.devsofthecoast.teammanagementdemo.commons.services.teams

import cat.devsofthecoast.teammanagementdemo.commons.cache.TMDCache
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

interface TeamsService {
    fun setTeams(teams: List<Team>, listener: ServiceCallback<Boolean>?)
    fun getTeams(serviceCallback: ServiceCallback<ArrayList<Team>>, trainersCache: TMDCache<String, ArrayList<Team>>)
    fun setTeam(input: Team, serviceCallback: ServiceCallback<Void?>)
    fun getTeam(key: String, serviceCallback: ServiceCallback<Team>)
}