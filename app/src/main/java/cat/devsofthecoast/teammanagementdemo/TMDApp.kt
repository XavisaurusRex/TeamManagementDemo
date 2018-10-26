package cat.devsofthecoast.teammanagementdemo

import android.app.Application
import cat.devsofthecoast.teammanagementdemo.commons.config.TMDAppConfig
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.dependencyinjection.TMDAppComponent

class TMDApp : Application() {
    private val config by lazy { TMDAppConfig() }

    fun getConfig(): BaseConfig {
        return config
    }

    private val appComponent by lazy {
        TMDAppComponent(config)
    }

    val presenterModule by lazy {
        appComponent.presenterModule
    }
}