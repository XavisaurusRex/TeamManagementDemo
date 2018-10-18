package cat.devsofthecoast.teammanagementdemo.feature.commons.repository

import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.impl.Team
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.impl.TeamsResult

interface TMDRepository {
    fun getTeams(): List<Team>?
}