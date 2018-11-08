package cat.devsofthecoast.teammanagementdemo.commons.repository.teams.impl

import cat.devsofthecoast.teammanagementdemo.commons.models.DatabaseModel
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.repository.teams.TeamsRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback
import cat.devsofthecoast.teammanagementdemo.commons.services.teams.impl.TeamsServiceImpl

class TeamsRepositoryImpl : TeamsRepository {

    private val service = TeamsServiceImpl()

    override fun setTeams(teams: List<Team>, listener: ServiceCallback<Boolean>?) {
        service.setTeams(teams, listener)
    }

    override fun getTeams(serviceCallback: ServiceCallback<ArrayList<Team>>) {
        service.getTeams(serviceCallback)
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