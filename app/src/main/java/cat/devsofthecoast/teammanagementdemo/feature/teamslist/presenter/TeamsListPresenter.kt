package cat.devsofthecoast.teammanagementdemo.feature.teamslist.presenter

import cat.devsofthecoast.teammanagementdemo.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.feature.teamslist.TeamsListContract

class TeamsListPresenter(
        private val appConfig: BaseConfig)
    : TeamsListContract.Presenter() {
}