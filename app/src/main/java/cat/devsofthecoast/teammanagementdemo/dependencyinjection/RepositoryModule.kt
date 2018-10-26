package cat.devsofthecoast.teammanagementdemo.dependencyinjection

import cat.devsofthecoast.teammanagementdemo.commons.repository.TMDRepository
import cat.devsofthecoast.teammanagementdemo.commons.repository.impl.TMDRepositoryImpl

class RepositoryModule {
    val tmdRepository: TMDRepository by lazy {
        TMDRepositoryImpl()
    }
}
