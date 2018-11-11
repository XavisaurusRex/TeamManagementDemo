package cat.devsofthecoast.teammanagementdemo.commons.models

import android.provider.ContactsContract

class DailyEntry : DatabaseModel() {
    // key must be timestamp
    override var key: String? = null
    var teamResponses: HashMap<String, TeamResponses> = hashMapOf()

    class TeamResponses {
        var team: String? = null
        var survey: ArrayList<String> = arrayListOf()
        // <player, <question, Any>>
        var playersResponses: HashMap<String, HashMap<String, Any>> = hashMapOf()
    }
}