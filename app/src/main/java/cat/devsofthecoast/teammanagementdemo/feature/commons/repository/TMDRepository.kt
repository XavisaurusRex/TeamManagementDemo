package cat.devsofthecoast.teammanagementdemo.feature.commons.repository

import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.impl.Team

interface TMDRepository {
    fun getTeams(): List<Team>?
}