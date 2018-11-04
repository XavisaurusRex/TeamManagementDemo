package cat.devsofthecoast.teammanagementdemo.commons.repository.base

import cat.devsofthecoast.teammanagementdemo.commons.models.DatabaseModel
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

interface BaseRepository {
    fun assignKey(databaseModel: DatabaseModel)
    fun assignKeys(databaseModels: List<DatabaseModel>)
}
