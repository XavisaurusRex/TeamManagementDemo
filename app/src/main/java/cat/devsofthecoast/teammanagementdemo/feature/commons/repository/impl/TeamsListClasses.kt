package cat.devsofthecoast.teammanagementdemo.feature.commons.repository.impl

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class TeamsResult(val list: List<Team>)

@JsonIgnoreProperties(ignoreUnknown = true)
data class  Team(val team_id: String, val team_name: String, val team_players: List<String>, val team_survey: List<String>)

