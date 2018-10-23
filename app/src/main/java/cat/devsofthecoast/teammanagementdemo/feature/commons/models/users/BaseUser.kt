package cat.devsofthecoast.teammanagementdemo.feature.commons.models.users

abstract class BaseUser {
    abstract var key: String?
    abstract var name: String?
    abstract var surname: String?
    abstract var picture_url: String?
    abstract var email: String?
    abstract var phoneNumber: Int?
}