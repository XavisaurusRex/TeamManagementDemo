package cat.devsofthecoast.teammanagementdemo.commons.models.users

import cat.devsofthecoast.teammanagementdemo.commons.models.DatabaseModel

abstract class BaseUser: DatabaseModel() {
    abstract var name: String?
    abstract var surname: String?
    abstract var picture_url: String?
    abstract var email: String?
    abstract var phoneNumber: Int?
}