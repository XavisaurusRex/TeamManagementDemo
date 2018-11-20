package cat.devsofthecoast.teammanagementdemo.commons.repository.teams.impl

import android.util.Log
import cat.devsofthecoast.teammanagementdemo.commons.cache.TMDCache
import cat.devsofthecoast.teammanagementdemo.commons.models.DatabaseModel
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.repository.teams.TeamsRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback
import cat.devsofthecoast.teammanagementdemo.commons.services.teams.impl.TeamsServiceImpl

class TeamsRepositoryImpl : TeamsRepository {
    private val LOG_TAG = "TeamsRepositoryImpl"

    private val teamsCache: TMDCache<String, ArrayList<Team>> = TMDCache()
    private val service = TeamsServiceImpl()

    override fun setTeams(teams: List<Team>, listener: ServiceCallback<Boolean>?) {
        service.setTeams(teams, listener)
    }

    override fun setTeam(input: Team, serviceCallback: ServiceCallback<Void?>) {
        service.setTeam(input, serviceCallback)
    }

    override fun getTeams(serviceCallback: ServiceCallback<ArrayList<Team>>) {
        if(teamsCache.contains("ALL")){
            Log.d(LOG_TAG, "getTeams from cache")
            serviceCallback.onSuccess(teamsCache["ALL"]!!)
        } else {
            Log.d(LOG_TAG, "getTeams from firebase")
            service.getTeams(serviceCallback, teamsCache)
        }
    }

    override fun getTeam(key: String, serviceCallback: ServiceCallback<Team>) {
        service.getTeam(key, serviceCallback)
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