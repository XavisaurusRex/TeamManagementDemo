package cat.devsofthecoast.teammanagementdemo

import android.app.Application
import cat.devsofthecoast.teammanagementdemo.config.TMDAppConfig
import cat.devsofthecoast.teammanagementdemo.core.mvp.config.BaseConfig

class TMDApp : Application() {
    private val config by lazy { TMDAppConfig() }

    fun getConfig(): BaseConfig {
        return config
    }
}