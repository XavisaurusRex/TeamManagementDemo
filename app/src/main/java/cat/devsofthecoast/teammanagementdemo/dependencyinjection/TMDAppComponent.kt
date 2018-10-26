package cat.devsofthecoast.teammanagementdemo.dependencyinjection

import cat.devsofthecoast.teammanagementdemo.core.mvp.config.BaseConfig

class TMDAppComponent(
        val appConfig: BaseConfig
) {

    val presenterModule: PresenterModule by lazy {
        PresenterModule(appConfig, useCaseModule)
    }

    private val useCaseModule: UseCaseModule by lazy {
        UseCaseModule(appConfig, repositoryModule)
    }

    private val repositoryModule: RepositoryModule by lazy {
        RepositoryModule()
    }
}