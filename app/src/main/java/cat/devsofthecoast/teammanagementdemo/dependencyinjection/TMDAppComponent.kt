package cat.devsofthecoast.teammanagementdemo.dependencyinjection

import android.content.Context
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig

class TMDAppComponent(
        val appConfig: BaseConfig,
        val context: Context) {

    val presenterModule: PresenterModule by lazy {
        PresenterModule(appConfig, useCaseModule)
    }

    private val useCaseModule: UseCaseModule by lazy {
        UseCaseModule(appConfig, context, repositoryModule)
    }

    private val repositoryModule: RepositoryModule by lazy {
        RepositoryModule()
    }
}