package cat.devsofthecoast.teammanagementdemo.feature.activities.selectteam.adapter

import cat.devsofthecoast.teammanagementdemo.commons.models.Team

interface TeamsAdapter {
    fun add(team: Team)
    fun add(teams: List<Team>)

    fun remove(team: Team)
    fun removeAt(position: Int)

    fun size(): Int
    fun removeAll()
}