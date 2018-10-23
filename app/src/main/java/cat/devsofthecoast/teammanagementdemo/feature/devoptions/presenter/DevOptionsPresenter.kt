package cat.devsofthecoast.teammanagementdemo.feature.devoptions.presenter

import cat.devsofthecoast.teammanagementdemo.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.feature.devoptions.DevOptionsContract

class DevOptionsPresenter(
        private val appConfig: BaseConfig)
    : DevOptionsContract.Presenter() {
    override fun fillDatabase() {

    }

}