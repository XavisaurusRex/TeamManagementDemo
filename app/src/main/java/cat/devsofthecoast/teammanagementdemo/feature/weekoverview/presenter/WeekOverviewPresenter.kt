package cat.devsofthecoast.teammanagementdemo.feature.weekoverview.presenter

import cat.devsofthecoast.teammanagementdemo.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.feature.weekoverview.WeekOverviewContract

class WeekOverviewPresenter(
        private val appConfig: BaseConfig)
    : WeekOverviewContract.Presenter() {

}