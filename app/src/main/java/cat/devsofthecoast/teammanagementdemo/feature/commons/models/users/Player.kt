package cat.devsofthecoast.teammanagementdemo.feature.commons.models.users

class Player : BaseUser() {
    override var key: String? = null
    override var name: String? = null
    override var surname: String? = null
    override var picture_url: String? = null
    override var email: String? = null

    override var phoneNumber: Int? = null
    var team: String? = null
    var physical: Physical? = null

    data class Physical(var height: Double, var weight: Double)
}