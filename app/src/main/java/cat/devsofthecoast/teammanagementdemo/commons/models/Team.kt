package cat.devsofthecoast.teammanagementdemo.commons.models

class Team: DatabaseModel() {
    override var key: String? = null
    var name: String? = null
    var logo_url: String? = null

    var survey: ArrayList<String> = arrayListOf()
    var players: ArrayList<String> = arrayListOf()
    var trainer: String? = null
}