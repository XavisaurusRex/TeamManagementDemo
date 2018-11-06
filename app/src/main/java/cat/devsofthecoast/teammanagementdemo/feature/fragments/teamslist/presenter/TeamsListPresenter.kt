package cat.devsofthecoast.teammanagementdemo.feature.fragments.teamslist.presenter

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.feature.fragments.teamslist.TeamsListContract

class TeamsListPresenter(
        private val appConfig: BaseConfig)
    : TeamsListContract.Presenter() {
}