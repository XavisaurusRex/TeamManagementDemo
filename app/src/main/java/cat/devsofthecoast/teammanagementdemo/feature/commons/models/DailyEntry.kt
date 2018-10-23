package cat.devsofthecoast.teammanagementdemo.feature.commons.models

class DailyEntry {
    var timestamp: String? = null
    var responses: Map<String, TeamResponses>? = mapOf()

    inner class TeamResponses {
        var team_key: String? = null
        var survey: List<String>? = null
        var responses: Map<String, Any>? = null
    }
}