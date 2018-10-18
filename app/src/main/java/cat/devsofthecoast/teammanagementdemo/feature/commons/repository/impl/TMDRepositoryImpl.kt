package cat.devsofthecoast.teammanagementdemo.feature.commons.repository.impl

import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.TMDRepository

class TMDRepositoryImpl(private val service: TMDService) : TMDRepository {
    override fun getTeams(): List<Team>? =
            service.getTeams().execute().body()
}